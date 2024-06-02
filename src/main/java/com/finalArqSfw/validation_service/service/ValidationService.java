package com.finalArqSfw.validation_service.service;

import com.finalArqSfw.validation_service.dto.FascoldaRecord;
import com.finalArqSfw.validation_service.entity.ValidationResult;
import com.finalArqSfw.validation_service.repository.ValidationResultRepository;
import com.finalArqSfw.validation_service.response.Response;
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


    public Response createValidationResult(String vehiclePlate, String idClient){
        String url = FASECOLDA_SERVICE_URL.concat(vehiclePlate);
        ValidationResult validationResult = new ValidationResult();
        validationResult.setIdClient(idClient);
        validationResult.setVehiclePlate(vehiclePlate);
        validationResult.setStatus("rechazada");
        FascoldaRecord record = restTemplate.getForObject(url, FascoldaRecord.class);
        int points = 0;
        if (record.getSoloLatas()>=1){
            points += record.getSoloLatas()*100;
        }
        if (record.getHeridos()>=1){
            points += record.getHeridos()*200;
        }
        if (record.getMuertos()>=1){
            points += record.getMuertos()*300;
        }

        if (points>=400){
            validationResult.setStatus("rechazada");
        } else {
            validationResult.setStatus("aprobada");
        }
        validationResult.setPoints(points);
        validationResultRepository.save(validationResult);
        Response response = new Response();
        if (validationResult.getStatus().equals("aprobada")){
            response.setCotizacion(true);
        } else {
            response.setCotizacion(false);
        }
        return response;
    }

    public ValidationResult saveResult(ValidationResult validationResult){
        return validationResultRepository.save(validationResult);
    }

}
