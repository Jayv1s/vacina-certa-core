package com.vacinacerta.usecase.uservaccine;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.mapper.UsersVaccinesMapper;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Qualifier("GetUsersVaccines")
public class GetUsersVaccinesUseCase implements IUseCase<UserContext, List<UsersVaccinesViewModel>> {

    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public List<UsersVaccinesViewModel> execute(UserContext userContext) {
        //TODO: Verify if user exists;

        List<UsersVaccinesDTO> usersVaccinesDTOList = vacinaCertaDbQueryGatewayImpl.getAllVaccinesFromUser(
                userContext.getUserId()
        );

        List<UsersVaccinesViewModel> usersVaccinesViewModelList = new ArrayList<>();

        for (UsersVaccinesDTO usersVaccinesDTO: usersVaccinesDTOList) {
            usersVaccinesViewModelList.add(UsersVaccinesMapper.convertToUsersVaccinesViewModel(usersVaccinesDTO));
        }

        return usersVaccinesViewModelList;
    }
}
