package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaAuthGateway;
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
@Qualifier("UpdateUserPasswordUseCase")
public class UpdateUserPasswordUseCase implements IUseCase<UserContext, Void> {

    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;
    @Autowired
    private final IVacinaCertaAuthGateway vacinaCertaAuthGateway;

    @Override
    public Void execute(UserContext userContext) throws BusinessLogicException {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId(),userContext.getJwtToken());

        if(Objects.isNull(userDTO)) {
            String errorMsg = String.format("User of ID: %s not found", userContext.getUserId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }

        try {
            if(Objects.nonNull(userContext.getNewPassword()) && Objects.nonNull(userContext.getActualPassword())) {
                vacinaCertaAuthGateway.updateUserPassword(userContext);
            } else {
                throw new BusinessLogicException("Missing password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            throw new BusinessLogicException("Password doesn't match", HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
