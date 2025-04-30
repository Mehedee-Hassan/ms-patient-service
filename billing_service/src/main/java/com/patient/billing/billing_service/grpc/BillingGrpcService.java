package com.patient.billing.billing_service.grpc;


import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GRpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    @Override
    public void createBillingAccount(
            BillingRequest billingRequest,
            StreamObserver<BillingResponse> responseStreamObserver){

        log.info("CreateBillingService request received: {}",billingRequest.toString());

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("1234")  // properties from BillingResponse
                .setStatus("ACTIVE")
                .build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();

    }

}
