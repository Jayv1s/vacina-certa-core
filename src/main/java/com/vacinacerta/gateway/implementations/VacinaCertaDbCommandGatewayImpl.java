package com.vacinacerta.gateway.implementations;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VacinaCertaDbCommandGatewayImpl implements IVacinaCertaDbCommandGateway {

    @Value(value = "${gateway.urls.vacina-certa-command}")
    private String BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public String insertUser(UserDTO userToInsert, String jwtToken) throws RestClientException {
        String url = BASE_URL.concat(ApiConstants.USER_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);

        HttpEntity<UserDTO> req = new HttpEntity<>(userToInsert, headers);

        try {
            return restTemplate.postForObject(url, req, String.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Void updateUser(String userId, UserDTO updatedUserData, String jwtToken) throws RestClientException {
        String url = BASE_URL.concat(ApiConstants.USER_PATH).concat(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);

        HttpEntity<UserDTO> req = new HttpEntity<>(updatedUserData, headers);

        try {
            restTemplate.put(url,req);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }

        return null;
    }

    @Override
    public UsersVaccinesDTO insertVaccineIntoUser(UsersVaccinesDTO usersVaccinesDTO, String jwtToken) throws RestClientException {
        String url = BASE_URL.concat(ApiConstants.USERS_PATH).concat(usersVaccinesDTO.getUserDTO().getId()).concat(ApiConstants.VACCINES_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);

        HttpEntity<UsersVaccinesDTO> req = new HttpEntity<>(usersVaccinesDTO, headers);

        try {
            return restTemplate.postForObject(url, req, UsersVaccinesDTO.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Void insertVaccineBatchIntoUser(String userId, List<String> vaccineIds, String jwtToken) throws RestClientException {
        String url = BASE_URL.concat(ApiConstants.USERS_PATH).concat(userId).concat(ApiConstants.VACCINES_PATH).concat(ApiConstants.BATCH_PATH);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);

        HttpEntity<List<String>> req = new HttpEntity<>(vaccineIds, headers);

        try {
            return restTemplate.postForObject(url, req, Void.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
