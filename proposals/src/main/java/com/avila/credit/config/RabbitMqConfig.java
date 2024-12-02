package com.avila.credit.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Configuration public class RabbitMqConfig {

    private final BrokerConfigurationProperties config;
    private final List<Queue> queues = new ArrayList<>();
    private final List<FanoutExchange> exchanges = new ArrayList<>();

    @Bean MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean Declarables queues() {
        assert Objects.nonNull(config);
        assert Objects.nonNull(config.getQueues());

        config.getQueues().values().stream()
                .filter(Objects::nonNull)
                .map(properties -> QueueBuilder.durable(properties.getName()).build())
                .forEach(queues::add);

        return new Declarables(queues);
    }

    @Bean Declarables exchanges() {
        assert Objects.nonNull(config);
        assert Objects.nonNull(config.getExchanges());

        config.getExchanges().values().stream()
                .filter(Objects::nonNull)
                .map(properties -> (FanoutExchange) ExchangeBuilder.fanoutExchange(properties.getName()).build())
                .forEach(exchanges::add);

        return new Declarables(exchanges);
    }

    @Bean Declarables bindings() {
        assert Objects.nonNull(config);
        assert Objects.nonNull(config.getBindings());

        List<Binding> bindings = config.getBindings().values().stream()
                .filter(Objects::nonNull)
                .map(properties -> {
                    Queue queue = queues.stream()
                            .filter(q -> properties.getQueueName().equals(q.getName()))
                            .findFirst()
                            .orElseThrow();

                    FanoutExchange exchange = exchanges.stream()
                            .filter(ex -> properties.getExchangeName().equals(ex.getName()))
                            .findFirst()
                            .orElseThrow();

                    return BindingBuilder.bind(queue)
                            .to(exchange);
                })
                .toList();

        return new Declarables(bindings);
    }

}
