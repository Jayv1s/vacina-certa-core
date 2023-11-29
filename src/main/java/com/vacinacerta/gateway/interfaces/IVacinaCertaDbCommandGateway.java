package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import org.springframework.web.client.RestClientException;

import java.util.List;

public interface IVacinaCertaDbCommandGateway {
    String insertUser(UserDTO userToInsert, String jwtToken) throws RestClientException;

    Void updateUser(String userId, UserDTO updatedUserData, String jwtToken) throws RestClientException;

    UsersVaccinesDTO insertVaccineIntoUser(UsersVaccinesDTO usersVaccinesDTO, String jwtToken) throws RestClientException;

    Void insertVaccineBatchIntoUser(String userId, List<String> vaccineIds, String jwtToken) throws RestClientException;
}
