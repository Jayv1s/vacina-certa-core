package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.dto.VaccineDTO;
import com.vacinacerta.model.mapper.UsersVaccinesMapper;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("AddVaccineToUser")
public class AddVaccineToUser implements IUseCase<UserContext, UsersVaccinesViewModel> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;

    @Override
    public UsersVaccinesViewModel execute(UserContext userContext) {
        //TODO: Adicionar lógica para validar se existe vacina E user;
        UsersVaccinesDTO usersVaccinesDTOToBeInserted = UsersVaccinesDTO.builder()
                .vaccineDTO(
                        VaccineDTO.builder().id(userContext.getVaccineId()).build()
                )
                .userDTO(
                        UserDTO.builder().id(userContext.getUserId()).build()
                )
                .build();

        UsersVaccinesDTO usersVaccinesDTOResult = vacinaCertaDbCommandGatewayImpl.insertVaccineIntoUser(usersVaccinesDTOToBeInserted);

        return UsersVaccinesMapper.convertToUsersVaccinesViewModel(usersVaccinesDTOResult);
    }
}
