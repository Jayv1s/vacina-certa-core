package com.vacinacerta.usecase.uservaccine;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.exception.BusinessLogicException;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.mapper.UsersVaccinesMapper;
import com.vacinacerta.model.view.UsersVaccinesViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Qualifier("GetUsersVaccines")
public class GetUsersVaccinesUseCase implements IUseCase<UserContext, List<UsersVaccinesViewModel>>  {

    @Autowired
    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public List<UsersVaccinesViewModel> execute(UserContext userContext) throws BusinessLogicException {
        UserDTO userDTO = vacinaCertaDbQueryGatewayImpl.getUserData(userContext.getUserId(), userContext.getJwtToken());

        if(Objects.isNull(userDTO)) {
            String errorMsg = String.format("User of ID: %s not found", userContext.getUserId());
            throw new BusinessLogicException(errorMsg, HttpStatus.NOT_FOUND);
        }

        List<UsersVaccinesDTO> usersVaccinesDTOList = vacinaCertaDbQueryGatewayImpl.getAllVaccinesFromUser(
                userContext.getUserId(),
                userContext.getJwtToken()
        );

        List<UsersVaccinesViewModel> usersVaccinesViewModelList = new ArrayList<>();

        for (UsersVaccinesDTO usersVaccinesDTO: usersVaccinesDTOList) {
            usersVaccinesViewModelList.add(UsersVaccinesMapper.convertToUsersVaccinesViewModel(usersVaccinesDTO));
        }

        return usersVaccinesViewModelList;
    }
}
