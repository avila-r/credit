package com.avila.notifications.listener;

import com.avila.notifications.domain.ProposalResponse;

import com.avila.notifications.service.MessagingService;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component public class PendingProposalsListener {

    private final MessagingService service;

    @Value("${sms.messages.pending-proposal}")
    private String PENDING_PROPOSAL;

    @RabbitListener(queues = "${broker.queue.name}")
    void listen(ProposalResponse response) {
        String message = String.format(PENDING_PROPOSAL, response.name());
        service.send(response.contact(), message);
    }

}
