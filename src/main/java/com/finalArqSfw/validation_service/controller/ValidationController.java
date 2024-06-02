package com.finalArqSfw.validation_service.controller;

import com.finalArqSfw.validation_service.entity.ValidationResult;
import com.finalArqSfw.validation_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validations")
public class ValidationController {
    @Autowired
    private ValidationService validationService;

    @PostMapping("/result")
    public ResponseEntity<ValidationResult> createValidationResult(@RequestBody ValidationResult validationResult){
        ValidationResult result = validationService.saveResult(validationResult);
        return ResponseEntity.ok(result);
    }
}
