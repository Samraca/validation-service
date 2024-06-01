package com.finalArqSfw.validation_service.repository;

import com.finalArqSfw.validation_service.entity.ValidationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidationResultRepository extends JpaRepository<ValidationResult, Long> {
    List<ValidationResult> findByValidationRequestId(Long requestId);
}
