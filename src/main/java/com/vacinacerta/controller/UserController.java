package com.vacinacerta.controller;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.model.mapper.UserMapper;
import com.vacinacerta.model.view.UserViewModel;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUseCase<UserContext, String> createUser;
    private final IUseCase<UserContext, Void> updateUser;
    private final IUseCase<UserContext, UsersVaccinesViewModel> addVaccineToUser;
    private final IUseCase<UserContext, List<UsersVaccinesViewModel>> getUsersVaccines;
    private final IUseCase<UserContext, UserViewModel> getUserData;

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
            IUseCase<UserContext, UserViewModel> getUserData
    ){
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.addVaccineToUser = addVaccineToUser;
        this.getUsersVaccines = getUsersVaccines;
        this.getUserData = getUserData;
    }

    @PostMapping()
    private ResponseEntity<Object> createUser(@RequestBody UserViewModel userViewModel) {
        try {
            UserContext context = UserContext.builder()
                    .userDTO(UserMapper.convertToUsersDTO(userViewModel))
                    .build();

            String result = createUser.execute(context);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RestClientException restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    private ResponseEntity<Object> updateUser(@PathVariable String userId, @RequestBody UserViewModel userViewModel) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .userDTO(UserMapper.convertToUsersDTO(userViewModel))
                    .build();

            updateUser.execute(context);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (RestClientException restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{userId}/{vaccineId}")
    private ResponseEntity<Object> insertVaccineIntoUser(@PathVariable String userId, @PathVariable String vaccineId) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .vaccineId(vaccineId)
                    .build();

            UsersVaccinesViewModel response = addVaccineToUser.execute(context);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RestClientException restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/vaccines")
    private ResponseEntity<Object> getAllVaccinesFromUser(@PathVariable String userId) {
        try {
            UserContext userContext = UserContext.builder()
                    .userId(userId)
                    .build();

            List<UsersVaccinesViewModel> response = getUsersVaccines.execute(userContext);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RestClientException restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    private ResponseEntity<Object> getUserData(@PathVariable String userId) {
        try {
            UserContext context = UserContext.builder()
                    .userId(userId)
                    .build();

            UserViewModel response = getUserData.execute(context);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RestClientException restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
