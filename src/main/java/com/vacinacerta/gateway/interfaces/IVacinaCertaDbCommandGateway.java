package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.request.UserVaccineRequest;
import org.springframework.web.client.RestClientException;

import java.util.List;

public interface IVacinaCertaDbCommandGateway {
    String insertUser(UserDTO userToInsert, String jwtToken) throws RestClientException;

    Void updateUser(String userId, UserDTO updatedUserData, String jwtToken) throws RestClientException;

    UsersVaccinesDTO insertVaccineIntoUser(UserContext context, String jwtToken) throws RestClientException;

    Void insertVaccineBatchIntoUser(String userId, List<UserVaccineRequest> vaccineIds, String jwtToken) throws RestClientException;
}
