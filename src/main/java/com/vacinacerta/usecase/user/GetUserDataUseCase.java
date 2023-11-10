package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.mapper.UserMapper;
import com.vacinacerta.model.view.UserViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Qualifier("GetUserData")
public class GetUserDataUseCase implements IUseCase<UserContext, UserViewModel> {

    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public UserViewModel execute(UserContext userContext) throws BusinessLogicException {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId());

        if(Objects.isNull(userDTO)) {
            String errorMsg = String.format("User of ID: %s not found", userContext.getUserId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }

        return UserMapper.convertToUserViewModel(userDTO);
    }
}
