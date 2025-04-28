package com.patient.service.v1.patient_service.service;

import com.patient.service.v1.patient_service.dto.PatientResponseDTO;
import com.patient.service.v1.patient_service.mapper.PatientMapper;
import com.patient.service.v1.patient_service.model.Patient;
import com.patient.service.v1.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS =
                (List<PatientResponseDTO>) patients.stream()
                        .map( PatientMapper::toDTO).toList();


        return patientResponseDTOS;
    }


}
