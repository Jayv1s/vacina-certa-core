package com.vacinacerta.gateway.implementations;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.model.dto.UserDTO;
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
        String uri = BASE_URL + "/user/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> req = new HttpEntity<>(userToInsert, headers);

        try {
            String userId = restTemplate.postForObject(uri, req, String.class);
            return userId;
        } catch (RestClientException exception) {
            System.out.println(exception);
            throw exception;
        }
    }
}
