package com.finalArqSfw.validation_service.service;

import com.finalArqSfw.validation_service.entity.ValidationRequest;
import com.finalArqSfw.validation_service.entity.ValidationResult;
import com.finalArqSfw.validation_service.repository.ValidationRequestRepository;
import com.finalArqSfw.validation_service.repository.ValidationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ValidationService {
    @Autowired
    private ValidationRequestRepository validationRequestRepository;

    @Autowired
    private ValidationResultRepository validationResultRepository;

    public ValidationRequest createRequest(String vehiclePlate){
        ValidationRequest request = new ValidationRequest();
        request.setVehiclePLate(vehiclePlate);
        request.setRequestDate(LocalDate.now());
        return validationRequestRepository.save(request);
    }

    public ValidationResult saveResult(ValidationResult validationResult){
        return validationResultRepository.save(validationResult);
    }

    public List<ValidationResult> getResultsByRequestId(Long requestId){
        return validationResultRepository.findByValidationRequestId(requestId);
    }

}
