package com.patient.service.v1.patient_service.controller;


import com.patient.service.v1.patient_service.dto.PatientRequestDTO;
import com.patient.service.v1.patient_service.dto.PatientResponseDTO;
import com.patient.service.v1.patient_service.model.Patient;
import com.patient.service.v1.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patientResponseDTOList = patientService.getPatients();
        return ResponseEntity.ok().body(patientResponseDTOList);
    }


    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid
            @RequestBody
            PatientRequestDTO patientRequestDTO
    )
    {
        PatientResponseDTO patientResponseDTO = patientService
                .createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }


}
