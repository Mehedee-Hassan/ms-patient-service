package com.patient.service.v1.patient_service.advicer;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
