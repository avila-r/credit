package com.avila.credit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "broker")
@Configuration public class BrokerConfigurationProperties {

    private Map<String, Queue> queues;
    private Map<String, Exchange> exchanges;
    private Map<String, Bind> bindings;

    @Data public static class Queue {
        private String name;
    }

    @Data public static class Exchange {
        private String name;
    }

    @Data public static class Bind {
        private String exchangeName;
        private String queueName;
    }

}
