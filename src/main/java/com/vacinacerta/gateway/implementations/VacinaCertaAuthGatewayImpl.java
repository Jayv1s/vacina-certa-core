package com.vacinacerta.gateway.implementations;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaAuthGateway;
import com.vacinacerta.model.request.NewPasswordRequest;
import com.vacinacerta.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class VacinaCertaAuthGatewayImpl implements IVacinaCertaAuthGateway {

    @Value(value = "${gateway.urls.vacina-certa-auth}")
    private String BASE_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Void updateUserPassword(UserContext userContext) {
        String url = BASE_URL
                .concat(ApiConstants.LOGIN)
                .concat(ApiConstants.UPDATE_PASSWORD)
                .concat(userContext.getUserId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        NewPasswordRequest newPasswordRequest = NewPasswordRequest.builder()
                .actualPassword(userContext.getActualPassword())
                .newPassword(userContext.getNewPassword())
                .build();

        HttpEntity<NewPasswordRequest> req = new HttpEntity<>(newPasswordRequest, headers);

        try {
            restTemplate.put(url, req);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }

        return null;
    }
}
