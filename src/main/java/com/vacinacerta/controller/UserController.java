package com.vacinacerta.controller;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.model.mapper.UserMapper;
import com.vacinacerta.model.view.UserViewModel;
import com.vacinacerta.usecase.IUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUseCase<UserContext, String> createUser;

    @Autowired
    private UserController(
            @Qualifier("CreateUser")
            IUseCase<UserContext, String> createUser
    ){
        this.createUser = createUser;
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
}
