package com.vacinacerta.usecase.uservaccine;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.request.UserVaccineRequest;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Qualifier("AddBatchOfVaccinesToUserUseCase")
public class AddBatchOfVaccinesToUserUseCase implements IUseCase<UserContext, Void> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGateway;
    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public Void execute(UserContext userContext) throws Exception {

        List<UsersVaccinesDTO> usersVaccinesDTOList = vacinaCertaDbQueryGatewayImpl.getAllVaccinesFromUser(userContext.getUserId(), userContext.getJwtToken());

        String listVaccinesId = "";

        for (UsersVaccinesDTO userVacciness : usersVaccinesDTOList) {
            listVaccinesId += "- " + userVacciness.getVaccineDTO().getId();
        }

        for(UserVaccineRequest userVaccineRequest: userContext.getVaccines()) {
            if(listVaccinesId.contains(userVaccineRequest.getVaccineDTO().getId())) {
                String errorMsg = String.format("Vaccine of ID: %s already added into User %s", userContext.getVaccineId(), userContext.getUserId());
                throw new BusinessLogicException(errorMsg, HttpStatus.BAD_REQUEST);
            }
        }

        vacinaCertaDbCommandGateway.insertVaccineBatchIntoUser(userContext.getUserId(), userContext.getVaccines(), userContext.getJwtToken());
        return null;
    }
}
