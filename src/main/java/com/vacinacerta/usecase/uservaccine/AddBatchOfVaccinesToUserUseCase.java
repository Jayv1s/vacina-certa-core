package com.vacinacerta.usecase.uservaccine;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("AddBatchOfVaccinesToUserUseCase")
public class AddBatchOfVaccinesToUserUseCase implements IUseCase<UserContext, Void> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGateway;

    @Override
    public Void execute(UserContext userContext) throws Exception {
        vacinaCertaDbCommandGateway.insertVaccineBatchIntoUser(userContext.getUserId(), userContext.getVaccines(), userContext.getJwtToken());
        return null;
    }
}
