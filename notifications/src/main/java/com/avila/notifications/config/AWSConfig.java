package com.avila.notifications.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class AWSConfig {

    @Bean AmazonSNS sns() {
        AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();

        return AmazonSNSClientBuilder.standard()
                .withCredentials(provider)
                .withRegion(Regions.SA_EAST_1)
                .build();
    }

}
