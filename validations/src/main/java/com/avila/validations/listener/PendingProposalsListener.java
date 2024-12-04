package com.avila.validations.listener;

import com.avila.validations.domain.Proposal;

import com.avila.validations.service.CreditEvaluationService;
import lombok.AllArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component class PendingProposalsListener {

    private final CreditEvaluationService service;

    @RabbitListener(queues = "${broker.queue.name}")
    void listen(Proposal response) {
        service.validate(response);
    }

}
