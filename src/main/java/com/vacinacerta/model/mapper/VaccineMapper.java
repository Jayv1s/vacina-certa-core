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
                .build();
    }

    public static VaccineViewModel convertToVaccineViewModel(VaccineDTO vaccineDTO) {
        return VaccineViewModel.builder()
                .age(vaccineDTO.getAge())
                .year(vaccineDTO.getYear())
                .createdAt(vaccineDTO.getCreatedAt())
                .fullName(vaccineDTO.getFullName())
                .id(vaccineDTO.getId())
                .manufacturer(vaccineDTO.getManufacturer())
                .popularName(vaccineDTO.getPopularName())
                .updatedAt(vaccineDTO.getUpdatedAt())
                .build();
    }
}
