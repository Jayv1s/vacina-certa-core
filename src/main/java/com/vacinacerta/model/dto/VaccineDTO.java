package com.vacinacerta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccineDTO {
    public String id;
    public String popularName;
    public String fullName;
    public String manufacturer;
    public Integer age;
    public Integer year;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    private Set<Object> vaccines;
}
