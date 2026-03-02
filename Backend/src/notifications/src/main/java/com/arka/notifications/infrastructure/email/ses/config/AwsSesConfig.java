package com.arka.notifications.infrastructure.email.ses.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class AwsSesConfig {

    @Value("${cloud-provider.aws.acces-key}")  
    private String accessKey;  
  
    @Value("${cloud-provider.aws.secret-key}")  
    private String secretKey;

    @Bean
    public SesClient sesClient(){

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return SesClient.builder()
            .region(Region.US_EAST_2)
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .build();
    }
    
}
