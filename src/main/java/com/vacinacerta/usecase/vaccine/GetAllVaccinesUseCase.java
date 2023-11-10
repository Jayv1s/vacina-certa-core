package com.vacinacerta.usecase.vaccine;

import com.vacinacerta.gateway.interfaces.IVacinaCertaDbQueryGateway;
import com.vacinacerta.model.dto.VaccineDTO;
import com.vacinacerta.model.mapper.VaccineMapper;
import com.vacinacerta.model.view.VaccineViewModel;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Qualifier("GetAllVaccines")
public class GetAllVaccinesUseCase implements IUseCase<Void, List<VaccineViewModel>> {

    private final IVacinaCertaDbQueryGateway vacinaCertaDbQueryGatewayImpl;

    @Override
    public  List<VaccineViewModel> execute(Void unused) {

        List<VaccineDTO> vaccineDTOS = vacinaCertaDbQueryGatewayImpl.getAllVaccines();

        List<VaccineViewModel> vaccineViewModelList = new ArrayList<>();

        for(VaccineDTO vaccineDTO: vaccineDTOS) {
            vaccineViewModelList.add(VaccineMapper.convertToVaccineViewModel(vaccineDTO));
        }

        return vaccineViewModelList;
    }
}
