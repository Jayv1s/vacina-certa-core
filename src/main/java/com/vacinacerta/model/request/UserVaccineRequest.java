package com.vacinacerta.model.request;

import com.vacinacerta.model.dto.VaccineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVaccineRequest {
    VaccineDTO vaccineDTO;
    Date appliedAt;
}
