package com.vacinacerta.model.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaccineViewModel {
    public String id;
    public String popularName;
    public String fullName;
    public String manufacturer;
    public Integer age;
    public Integer year;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    private Set<Object> vaccines;
    private String dosage;
    private String description;
    private Boolean required;
}
