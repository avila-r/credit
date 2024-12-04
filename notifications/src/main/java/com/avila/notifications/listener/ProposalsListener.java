package com.avila.notifications.listener;

import com.avila.notifications.domain.ProposalResponse;
import com.avila.notifications.service.MessagingService;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component class ProposalsListener {

    private final MessagingService service;

    @RabbitListener(queues = "${broker.queue.default}")
    void listen(ProposalResponse response) {
        service.send(response.contact(), response.observation());
    }

}
