package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import org.springframework.web.client.RestClientException;

public interface IVacinaCertaDbCommandGateway {
    String insertUser(UserDTO userToInsert) throws RestClientException;

    Void updateUser(String userId, UserDTO updatedUserData) throws RestClientException;

    UsersVaccinesDTO insertVaccineIntoUser(UsersVaccinesDTO usersVaccinesDTO) throws RestClientException;
}
