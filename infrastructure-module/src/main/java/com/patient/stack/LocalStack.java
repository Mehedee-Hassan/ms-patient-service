package com.patient.stack;

import software.amazon.awscdk.*;

public class LocalStack extends Stack {
    public LocalStack(final App scope, final String id, final StackProps props ){
        super(scope, id, props);
    }


    public static void main(final String[] args){
        App app = new App(AppProps.builder().outdir("./cdk.out").build());

        // converts java code to infrastructure
        StackProps props = StackProps.builder().synthesizer(new BootstraplessSynthesizer())
                .build();


        new LocalStack(app,"localstack",props);
        app.synth();
        System.out.println("App synthesizing in progress...");

    }
}
