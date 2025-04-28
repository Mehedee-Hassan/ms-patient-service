package com.patient.service.v1.patient_service.mapper;

import com.patient.service.v1.patient_service.dto.PatientResponseDTO;
import com.patient.service.v1.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){

        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }
}
