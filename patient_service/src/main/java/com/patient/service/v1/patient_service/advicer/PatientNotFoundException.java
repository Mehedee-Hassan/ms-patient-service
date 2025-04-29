package com.patient.service.v1.patient_service.advicer;

import java.util.UUID;

public class PatientNotFoundException extends Throwable {
    public PatientNotFoundException(String message, UUID id) {
        super(message);
    }
}
