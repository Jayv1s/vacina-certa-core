package com.vacinacerta.usecase.User;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("CreateUser")
public class CreateUser implements IUseCase<UserContext, String> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;

    @Override
    public String execute(UserContext userContext) {
        //TODO: criar logica de BUSCAR email, CASO encontre -> NAO CRIAR NOVO USUARIO, CASO NÃO encontre -> criar usuário
        String userId = vacinaCertaDbCommandGatewayImpl.insertUser(userContext.getUserDTO());
        return userId;
    }
}
