package com.finalArqSfw.validation_service.controller;

import com.finalArqSfw.validation_service.entity.ValidationRequest;
import com.finalArqSfw.validation_service.entity.ValidationResult;
import com.finalArqSfw.validation_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/validations")
public class ValidationController {
    @Autowired
    private ValidationService validationService;

    @PostMapping("/request")
    public ResponseEntity<ValidationRequest> createValidationResult(@RequestParam String vehiclePlate){
        ValidationRequest request = validationService.createRequest(vehiclePlate);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/result")
    public ResponseEntity<ValidationResult> createValidationResult(@RequestBody ValidationResult validationResult){
        ValidationResult result = validationService.saveResult(validationResult);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/results/{requestId}")
    public ResponseEntity<List<ValidationResult>> getValidationResults(@PathVariable Long requestId){
        List<ValidationResult> results = validationService.getResultsByRequestId(requestId);
        return ResponseEntity.ok(results);
    }
}
