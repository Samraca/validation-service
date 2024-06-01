package com.finalArqSfw.validation_service.repository;

import com.finalArqSfw.validation_service.entity.ValidationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationRequestRepository extends JpaRepository<ValidationRequest, Long> {
}
