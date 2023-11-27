package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.dto.VaccineDTO;

import java.util.List;

public interface IVacinaCertaDbQueryGateway {
    List<VaccineDTO> getAllVaccines(String jwtToken);

    List<UsersVaccinesDTO> getAllVaccinesFromUser(String userId, String jwtToken);

    UserDTO getUserData(String userId, String jwtToken);

    VaccineDTO getVaccineData(String vaccineId, String jwtToken);
}
