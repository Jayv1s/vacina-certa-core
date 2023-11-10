package com.vacinacerta.controller;

import com.vacinacerta.model.view.VaccineViewModel;
import com.vacinacerta.usecase.IUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/vaccine")
public class VaccineController {

    private final IUseCase<Void, List<VaccineViewModel>> getAllVaccines;

    @Autowired
    private VaccineController(
            @Qualifier("GetAllVaccines")
            IUseCase<Void, List<VaccineViewModel>> getAllVaccines
    ) {
        this.getAllVaccines = getAllVaccines;
    }

    @GetMapping()
    private ResponseEntity<Object> getAllVaccines() {
        try {
            var response = getAllVaccines.execute(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception restClientException) {
            return new ResponseEntity<>(restClientException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
