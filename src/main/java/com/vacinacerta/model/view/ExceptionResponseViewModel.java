package com.vacinacerta.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponseViewModel {
    private String errorMessage;
    private HttpStatus statusCode;
}
