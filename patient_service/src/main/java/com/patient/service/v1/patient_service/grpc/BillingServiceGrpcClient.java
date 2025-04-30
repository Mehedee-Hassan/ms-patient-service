package com.patient.service.v1.patient_service.grpc;


import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private BillingServiceGrpc.BillingServiceBlockingStub blockingStub = null;


    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:6565}") int serverPort
            ){
        log.info("Connecting to Billing Service GRPC service at {} : {}",serverAddress,serverPort);

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(serverAddress,serverPort)
                .usePlaintext()
                .build();

        blockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
    }

    public void createBillingAccount(String patientId, String name, String email){
        BillingRequest request = BillingRequest.newBuilder().setPatientId(email).build();
        BillingResponse response;
        response = blockingStub.createBillingAccount(request);
        log.info("Billing service response : {}",response);
    }
}
