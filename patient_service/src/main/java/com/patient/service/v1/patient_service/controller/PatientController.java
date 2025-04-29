package com.patient.service.v1.patient_service.controller;


import com.patient.service.v1.patient_service.advicer.PatientNotFoundException;
import com.patient.service.v1.patient_service.dto.PatientRequestDTO;
import com.patient.service.v1.patient_service.dto.PatientResponseDTO;
import com.patient.service.v1.patient_service.dto.validators.CreatePatientValidationGroup;
import com.patient.service.v1.patient_service.model.Patient;
import com.patient.service.v1.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("")
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patientResponseDTOList = patientService.getPatients();
        return ResponseEntity.ok().body(patientResponseDTOList);
    }


    @PostMapping
    @Operation(summary = "Create Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated(
                    {Default.class, CreatePatientValidationGroup.class})
            @RequestBody
            PatientRequestDTO patientRequestDTO
    )
    {
        PatientResponseDTO patientResponseDTO = patientService
                .createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDTO>
    updatePatient( @Validated({Default.class})
                   @PathVariable UUID id, @RequestBody PatientRequestDTO patientRequestDTO)
            throws PatientNotFoundException {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }



}
