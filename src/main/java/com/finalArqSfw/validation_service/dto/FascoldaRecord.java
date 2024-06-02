package com.finalArqSfw.validation_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FascoldaRecord {
    private Long id;
    private String vehiclePlate;
    private String severity;
    private LocalDate date;
}
