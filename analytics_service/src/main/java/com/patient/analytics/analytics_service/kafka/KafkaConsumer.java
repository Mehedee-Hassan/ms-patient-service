package com.patient.analytics.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {


    Logger log = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics = "patient", groupId = "analytics_service")
    public void consumeEvent(byte[] event)  {
        try{
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // call service layer
            log.error("Consumer analytics received patient events name={}, email = {}"
                    ,patientEvent.getName()
                    ,patientEvent.getEmail());


        }catch (Exception e){
            log.error("Error consuming and Deserialize event: {}",e.getMessage());
        }

    }
}
