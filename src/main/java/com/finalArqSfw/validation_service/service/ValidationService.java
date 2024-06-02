package com.finalArqSfw.validation_service.service;

import com.finalArqSfw.validation_service.dto.FascoldaRecord;
import com.finalArqSfw.validation_service.entity.ValidationResult;
import com.finalArqSfw.validation_service.repository.ValidationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationService {

    @Autowired
    private ValidationResultRepository validationResultRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String FASECOLDA_SERVICE_URL = "http://localhost:8080/api/accidents/";


    public ValidationResult createValidationResult(ValidationResult validationResult){
        String vehiclePlate = validationResult.getVehiclePlate();
        String url = FASECOLDA_SERVICE_URL + vehiclePlate;

        FascoldaRecord[] records = restTemplate.getForObject(url, FascoldaRecord[].class);
        try {
            int points = 0;
            for (FascoldaRecord accident : records){
                switch (accident.getSeverity()){
                    case "solo latas":
                        points +=100;
                        break;
                    case "heridos":
                        points +=200;
                        break;
                    case "muertos":
                        points +=300;
                        break;
                    default:
                        break;
                }
            }

            if (points>=400){
                validationResult.setStatus("rechazada");
            } else {
                validationResult.setStatus("aprobada");
            }
        } catch (NullPointerException e){
            validationResult.setStatus("aprobada");
        }
        return saveResult(validationResult);

    }

    public ValidationResult saveResult(ValidationResult validationResult){
        return validationResultRepository.save(validationResult);
    }

}
