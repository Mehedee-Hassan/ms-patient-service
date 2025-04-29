package com.patient.service.v1.patient_service.mapper;

import com.patient.service.v1.patient_service.dto.PatientRequestDTO;
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

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

        return patient;
    }
}
