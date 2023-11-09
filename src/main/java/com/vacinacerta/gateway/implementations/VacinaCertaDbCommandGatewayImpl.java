package com.vacinacerta.gateway.implementations;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class VacinaCertaDbCommandGatewayImpl implements IVacinaCertaDbCommandGateway {

    private static final String BASE_URL = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public String insertUser(UserDTO userToInsert) throws RestClientException {
        String url = BASE_URL + "/user/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> req = new HttpEntity<>(userToInsert, headers);

        try {
            return restTemplate.postForObject(url, req, String.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Void updateUser(String userId, UserDTO updatedUserData) throws RestClientException {
        String url = BASE_URL + "/user/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

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
    public UsersVaccinesDTO insertVaccineIntoUser(UsersVaccinesDTO usersVaccinesDTO) throws RestClientException {
        String url = BASE_URL + "/users/vaccines";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UsersVaccinesDTO> req = new HttpEntity<>(usersVaccinesDTO, headers);

        try {
            return restTemplate.postForObject(url, req, UsersVaccinesDTO.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
