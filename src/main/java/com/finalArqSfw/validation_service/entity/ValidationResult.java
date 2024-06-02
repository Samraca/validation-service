package com.finalArqSfw.validation_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValidationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vehiclePlate;
    private String idClient;
    private String status;
    private int points = 0;
}
