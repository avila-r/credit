package com.avila.proposals.service;

import com.avila.proposals.dto.ProposalResponse;
import com.avila.proposals.model.Proposal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service public class NotificationService {

    private final RabbitTemplate template;

    @Value("${broker.exchanges.proposals.name}")
    private String approved;

    @Value("${broker.exchanges.pending-proposals.name}")
    private String pending;

    public boolean notify(Proposal proposal) {
        try {
            String exchange = proposal.isApproved() ? approved : pending;
            template.convertAndSend(exchange, "", ProposalResponse.from(proposal));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}
