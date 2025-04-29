package com.patient.service.v1.patient_service.service;

import com.patient.service.v1.patient_service.advicer.EmailAlreadyExistsException;
import com.patient.service.v1.patient_service.dto.PatientRequestDTO;
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


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email" +
                    " already exists" + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }


}
