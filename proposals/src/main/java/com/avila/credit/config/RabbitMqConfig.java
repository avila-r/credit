package com.avila.credit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class RabbitMqConfig {

    @Bean FanoutExchange pendingExchange() {
        return ExchangeBuilder.fanoutExchange("pending.proposals.exchange")
                .build();
    }

    @Bean Binding bindPendingValidations() {
        return BindingBuilder.bind(pendingValidations())
                .to(pendingExchange());
    }

    @Bean Binding bindPendingNotification() {
        return BindingBuilder.bind(pendingNotifications())
                .to(pendingExchange());
    }

    @Bean
    public Queue pendingValidations() {
        return QueueBuilder.durable("pending.proposals.validation")
                .build();
    }

    @Bean
    public Queue pendingNotifications() {
        return QueueBuilder.durable("pending.proposals.notification")
                .build();
    }

    @Bean FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange("proposals.exchange")
                .build();
    }

    @Bean Binding bind() {
        return BindingBuilder.bind(proposals())
                .to(exchange());
    }

    @Bean Binding bindNotification() {
        return BindingBuilder.bind(notifications())
                .to(exchange());
    }

    @Bean
    public Queue proposals() {
        return QueueBuilder.durable("proposals")
                .build();
    }

    @Bean
    public Queue notifications() {
        return QueueBuilder.durable("proposals.notification")
                .build();
    }

}
