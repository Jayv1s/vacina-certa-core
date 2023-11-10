package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.mapper.UserMapper;
import com.vacinacerta.model.view.UserViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("GetUserData")
public class GetUserDataUseCase implements IUseCase<UserContext, UserViewModel> {

    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public UserViewModel execute(UserContext userContext) {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId());
        return UserMapper.convertToUserViewModel(userDTO);
    }
}
