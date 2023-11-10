package com.vacinacerta.gateway.implementations;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.dto.VaccineDTO;
import com.vacinacerta.utils.ApiConstants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VacinaCertaDbQueryGatewayImpl implements IVacinaCertaDbQueryGateway {

    private static final String BASE_URL = "http://localhost:8081";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<VaccineDTO> getAllVaccines() {
        String API_PATH = "list-all";
        String url = BASE_URL.concat(ApiConstants.VACCINE_PATH).concat(API_PATH);

        try {
            return restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<VaccineDTO>>(){}
            ).getBody();
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public List<UsersVaccinesDTO> getAllVaccinesFromUser(String userId) {
        String url = BASE_URL.concat(ApiConstants.USERS_PATH).concat(userId).concat(ApiConstants.VACCINES_PATH);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UsersVaccinesDTO>>(){}
            ).getBody();
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public UserDTO getUserData(String userId) {
        String url = BASE_URL.concat(ApiConstants.USER_PATH).concat(userId);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<UserDTO>() {}
            ).getBody();
        } catch (HttpClientErrorException exception) {
            System.out.println(exception.getMessage());

            if(exception.getStatusCode().is4xxClientError()) {
                return null;
            }

            throw exception;
        }
    }

    @Override
    public VaccineDTO getVaccineData(String vaccineId) {
        String url = BASE_URL.concat(ApiConstants.VACCINE_PATH).concat(vaccineId);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<VaccineDTO>() {}
            ).getBody();
        } catch (HttpClientErrorException exception) {
            System.out.println(exception.getMessage());

            if(exception.getStatusCode().is4xxClientError()) {
                return null;
            }

            throw exception;
        }
    }
}
