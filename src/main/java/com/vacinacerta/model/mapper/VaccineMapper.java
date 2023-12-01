package com.vacinacerta.model.mapper;

import com.vacinacerta.model.dto.VaccineDTO;
import com.vacinacerta.model.view.VaccineViewModel;

public class VaccineMapper {
    public static VaccineDTO convertToVaccineDTO(VaccineViewModel vaccineViewModel) {
        return VaccineDTO.builder()
                .age(vaccineViewModel.getAge())
                .year(vaccineViewModel.getYear())
                .createdAt(vaccineViewModel.getCreatedAt())
                .fullName(vaccineViewModel.getFullName())
                .id(vaccineViewModel.getId())
                .manufacturer(vaccineViewModel.getManufacturer())
                .popularName(vaccineViewModel.getPopularName())
                .updatedAt(vaccineViewModel.getUpdatedAt())
                .dosage(vaccineViewModel.getDosage())
                .description(vaccineViewModel.getDescription())
                .required(vaccineViewModel.getRequired())
                .build();
    }

    public static VaccineViewModel convertToVaccineViewModel(VaccineDTO vaccineDTO) {
        return VaccineViewModel.builder()
                .age(vaccineDTO.getAge())
                .year(vaccineDTO.getYear())
                .fullName(vaccineDTO.getFullName())
                .id(vaccineDTO.getId())
                .manufacturer(vaccineDTO.getManufacturer())
                .popularName(vaccineDTO.getPopularName())
                .dosage(vaccineDTO.getDosage())
                .description(vaccineDTO.getDescription())
                .required(vaccineDTO.getRequired())
                .build();
    }
}
