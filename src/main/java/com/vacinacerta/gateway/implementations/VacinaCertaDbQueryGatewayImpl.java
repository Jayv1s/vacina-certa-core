package com.vacinacerta.gateway.implementations;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.VaccineDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VacinaCertaDbQueryGatewayImpl implements IVacinaCertaDbQueryGateway {

    private static final String BASE_URL = "http://localhost:8081";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<VaccineDTO> getAllVaccines() {
        String url = BASE_URL + "vaccine/list-all";

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
}
