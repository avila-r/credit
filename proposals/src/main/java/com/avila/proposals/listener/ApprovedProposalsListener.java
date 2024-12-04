package com.avila.proposals.listener;

import com.avila.proposals.dto.ProposalResponse;
import com.avila.proposals.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component class ApprovedProposalsListener {

    private final ProposalService service;

    @RabbitListener(queues = "${broker.queues.proposals.name}")
    void listen(ProposalResponse proposal) {
        if (!proposal.approved()) {
            service.remove(proposal.id());
        }

        service.update(proposal.entity());
    }

}
