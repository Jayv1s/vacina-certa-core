package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Qualifier("UpdateUser")
public class UpdateUserUseCase implements IUseCase<UserContext, Void> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;
    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public Void execute(UserContext userContext) throws BusinessLogicException {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId());

        if(Objects.isNull(userDTO)) {
            String errorMsg = String.format("User of ID: %s not found", userContext.getUserId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }


        vacinaCertaDbCommandGatewayImpl.updateUser(userContext.getUserId(), userContext.getUserDTO());

        return null;
    }
}
