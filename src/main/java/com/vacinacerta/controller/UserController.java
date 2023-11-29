package com.vacinacerta.controller;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.model.mapper.UserMapper;
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
    private ResponseEntity<Object> insertVaccineIntoUser(@PathVariable String userId, @RequestBody List<String> vaccineIds, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            if (vaccineIds.size() == 1) {
                UserContext context = UserContext.builder()
                        .userId(userId)
                        .vaccineId(vaccineIds.get(0))
                        .jwtToken(authorizationToken.split(" ")[1])
                        .build();

                UsersVaccinesViewModel response = addVaccineToUser.execute(context);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            if (vaccineIds.size() > 1) {
                UserContext context = UserContext.builder()
                        .userId(userId)
                        .vaccines(vaccineIds)
                        .jwtToken(authorizationToken.split(" ")[1])
                        .build();

                addBatchOfVaccinesToUserUseCase.execute(context);
                return new ResponseEntity<>(HttpStatus.OK);
            }


            throw new Exception("Empty body");
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

    @GetMapping("/{userId}/vaccines")
    private ResponseEntity<Object> getAllVaccinesFromUser(@PathVariable String userId, @RequestHeader(value = "Authorization") String authorizationToken) {
        try {
            UserContext userContext = UserContext.builder()
                    .userId(userId)
                    .jwtToken(authorizationToken.split(" ")[1])
                    .build();

            List<UsersVaccinesViewModel> response = getUsersVaccines.execute(userContext);
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
