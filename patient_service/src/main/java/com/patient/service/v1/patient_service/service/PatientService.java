package com.patient.service.v1.patient_service.service;

import com.patient.service.v1.patient_service.advicer.EmailAlreadyExistsException;
import com.patient.service.v1.patient_service.advicer.PatientNotFoundException;
import com.patient.service.v1.patient_service.dto.PatientRequestDTO;
import com.patient.service.v1.patient_service.dto.PatientResponseDTO;
import com.patient.service.v1.patient_service.grpc.BillingServiceGrpcClient;
import com.patient.service.v1.patient_service.kafka.KafkaProducer;
import com.patient.service.v1.patient_service.mapper.PatientMapper;
import com.patient.service.v1.patient_service.model.Patient;
import com.patient.service.v1.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository,
                          BillingServiceGrpcClient billingServiceGrpcClient,
                          KafkaProducer kafkaProducer){
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer =kafkaProducer;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS =
                (List<PatientResponseDTO>) patients.stream()
                        .map( PatientMapper::toDTO).toList();


        return patientResponseDTOS;
    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO
    ){
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email" +
                    " already exists" + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        this.billingServiceGrpcClient
                .createBillingAccount(newPatient.getId().toString()
                ,newPatient.getName()
                ,newPatient.getEmail());


        kafkaProducer.sendEvent(newPatient);

        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO)
            throws PatientNotFoundException {
        Patient patient =  patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found Id: ", id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email" +
                    " already exists" + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patientRepository.save(patient);

        return PatientMapper.toDTO(patient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }

}
