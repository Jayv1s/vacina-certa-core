package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.model.dto.VaccineDTO;

import java.util.List;

public interface IVacinaCertaDbQueryGateway {
    List<VaccineDTO> getAllVaccines();
}
