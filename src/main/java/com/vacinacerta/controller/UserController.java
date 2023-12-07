package com.vacinacerta.controller;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.model.mapper.UserMapper;
import com.vacinacerta.model.request.UserVaccineRequest;
import com.vacinacerta.model.view.ExceptionResponseViewModel;
import com.vacinacerta.model.view.UserViewModel;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUseCase<UserContext, String> createUser;
    private final IUseCase<UserContext, Void> updateUser;
    private final IUseCase<UserContext, UsersVaccinesViewModel> addVaccineToUser;
    private final IUseCase<UserContext, List<UsersVaccinesViewModel>> getUsersVaccines;
    private final IUseCase<UserContext, UserViewModel> getUserData;
    private final IUseCase<UserContext, Void> addBatchOfVaccinesToUserUseCase;

    @Autowired
    private UserController(
            @Qualifier("CreateUser")
            IUseCase<UserContext, String> createUser,
            @Qualifier("UpdateUser")
            IUseCase<UserContext, Void> updateUser,
            @Qualifier("AddVaccineToUser")
            IUseCase<UserContext, UsersVaccinesViewModel> addVaccineToUser,
            @Qualifier("GetUsersVaccines")
            IUseCase<UserContext, List<UsersVaccinesViewModel>> getUsersVaccines,
            @Qualifier("GetUserData")
            IUseCase<UserContext, UserViewModel> getUserData,
            @Qualifier("AddBatchOfVaccinesToUserUseCase")
            IUseCase<UserContext, Void> addBatchOfVaccinesToUserUseCase
    ){
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.addVaccineToUser = addVaccineToUser;
        this.getUsersVaccines = getUsersVaccines;
        this.getUserData = getUserData;
        this.addBatchOfVaccinesToUserUseCase = addBatchOfVaccinesToUserUseCase;
    }

    @PostMapping()
    private ResponseEntity<Object> createUser(@RequestBody UserViewModel userViewModel, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            System.out.println(authorizationToken);
            UserContext context = UserContext.builder()
                    .userDTO(UserMapper.convertToUsersDTO(userViewModel))
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            String result = createUser.execute(context);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    private ResponseEntity<Object> updateUser(@PathVariable String userId, @RequestBody UserViewModel userViewModel, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .userDTO(UserMapper.convertToUsersDTO(userViewModel))
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            updateUser.execute(context);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception exception) {

            if(exception instanceof BusinessLogicException) {
                ExceptionResponseViewModel exceptionResponseViewModel = ExceptionResponseViewModel.builder()
                        .errorMessage(((BusinessLogicException) exception).getErrorMessage())
                        .statusCode(((BusinessLogicException) exception).getStatusCode())
                        .build();
                return new ResponseEntity<>(exceptionResponseViewModel, ((BusinessLogicException) exception).getStatusCode());
            }
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/vaccines")
    private ResponseEntity<Object> insertVaccineIntoUser(@PathVariable String userId, @RequestBody List<UserVaccineRequest> vaccines, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .vaccines(vaccines)
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            if (vaccines.size() == 1) {
                UsersVaccinesViewModel response = addVaccineToUser.execute(context);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (vaccines.size() > 1) {
                addBatchOfVaccinesToUserUseCase.execute(context);
                return new ResponseEntity<>(HttpStatus.OK);
            }


            throw new Exception("Empty body");
        } catch (Exception exception) {
            System.out.println(exception);
            if(exception instanceof BusinessLogicException) {
                ExceptionResponseViewModel exceptionResponseViewModel = ExceptionResponseViewModel.builder()
                        .errorMessage(((BusinessLogicException) exception).getErrorMessage())
                        .statusCode(((BusinessLogicException) exception).getStatusCode())
                        .build();
                return new ResponseEntity<>(exceptionResponseViewModel, ((BusinessLogicException) exception).getStatusCode());
            }
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/vaccines")
    private ResponseEntity<Object> getAllVaccinesFromUser(@PathVariable String userId, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            UserContext userContext = UserContext.builder()
                    .userId(userId)
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            List<UsersVaccinesViewModel> response = getUsersVaccines.execute(userContext);

            if (Objects.isNull(response)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {

            if(exception instanceof BusinessLogicException) {
                ExceptionResponseViewModel exceptionResponseViewModel = ExceptionResponseViewModel.builder()
                        .errorMessage(((BusinessLogicException) exception).getErrorMessage())
                        .statusCode(((BusinessLogicException) exception).getStatusCode())
                        .build();
                return new ResponseEntity<>(exceptionResponseViewModel, ((BusinessLogicException) exception).getStatusCode());
            }
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    private ResponseEntity<Object> getUserData(@PathVariable String userId, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            UserViewModel response = getUserData.execute(context);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {

            if(exception instanceof BusinessLogicException) {
                ExceptionResponseViewModel exceptionResponseViewModel = ExceptionResponseViewModel.builder()
                        .errorMessage(((BusinessLogicException) exception).getErrorMessage())
                        .statusCode(((BusinessLogicException) exception).getStatusCode())
                        .build();
                return new ResponseEntity<>(exceptionResponseViewModel, ((BusinessLogicException) exception).getStatusCode());
            }
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
