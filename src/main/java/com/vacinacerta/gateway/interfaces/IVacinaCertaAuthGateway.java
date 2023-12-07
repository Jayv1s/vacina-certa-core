package com.vacinacerta.gateway.interfaces;

import com.vacinacerta.context.UserContext;

public interface IVacinaCertaAuthGateway {

    Void updateUserPassword(UserContext userContext);
}
