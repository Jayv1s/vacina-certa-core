package com.vacinacerta.usecase.uservaccine;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.dto.VaccineDTO;
import com.vacinacerta.model.mapper.UsersVaccinesMapper;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Qualifier("AddVaccineToUser")
public class AddVaccineToUserUseCase implements IUseCase<UserContext, UsersVaccinesViewModel> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;
    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public UsersVaccinesViewModel execute(UserContext userContext) throws BusinessLogicException {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId(), userContext.getJwtToken());

        if(Objects.isNull(userDTO)) {
            String errorMsg = String.format("User of ID: %s not found", userContext.getUserId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }

        VaccineDTO vaccineDTO = vacinaCertaDbQueryGatewayImpl.getVaccineData(userContext.getVaccines().get(0).getVaccineDTO().getId(), userContext.getJwtToken());

        if(Objects.isNull(vaccineDTO)) {
            String errorMsg = String.format("Vaccine of ID: %s not found", userContext.getVaccineId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }

        List<UsersVaccinesDTO> usersVaccinesDTOList = vacinaCertaDbQueryGatewayImpl.getAllVaccinesFromUser(userContext.getUserId(), userContext.getJwtToken());

        for (UsersVaccinesDTO userVacciness : usersVaccinesDTOList) {
          if(userVacciness.getVaccineDTO().getId().equals(userContext.getVaccines().get(0).getVaccineDTO().getId())) {
              String errorMsg = String.format("Vaccine of ID: %s already added into User %s", userVacciness.getVaccineDTO().getId(), userContext.getUserId());
              throw new BusinessLogicException(errorMsg, HttpStatus.BAD_REQUEST);
          }
        }

        UsersVaccinesDTO usersVaccinesDTOResult = vacinaCertaDbCommandGatewayImpl.insertVaccineIntoUser(userContext, userContext.getJwtToken());

        return UsersVaccinesMapper.convertToUsersVaccinesViewModel(usersVaccinesDTOResult);
    }
}
