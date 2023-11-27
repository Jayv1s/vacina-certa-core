package com.vacinacerta.model.view;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
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
