package com.avila.validations.service;

import com.avila.validations.domain.Proposal;
import com.avila.validations.exceptions.StrategyException;
import com.avila.validations.service.strategy.CreditScoring;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service public class CreditEvaluationService {

    @Value("${broker.exchange.name}")
    private String exchange;

    private final RabbitTemplate template;

    @Value("${sms.observations.approved}")
    private String APPROVED_MESSAGE;

    @Value("${sms.observations.unapproved}")
    private String UNAPPROVED_MESSAGE;

    private final List<CreditScoring> strategies;

    public void validate(Proposal proposal) {
        try {
            boolean isApproved = strategies.stream()
                    .mapToInt(strategy -> strategy.validate(proposal))
                    .sum() > 350;

            Proposal evaluated = isApproved
                    ? proposal.update()
                            .approved(true)
                            .observation(String.format(APPROVED_MESSAGE, proposal.name()))
                            .build()
                    : proposal.update()
                            .approved(false)
                            .observation(String.format(UNAPPROVED_MESSAGE, proposal.name()))
                            .build();

            send(evaluated);
        } catch (StrategyException e) {
            Proposal unevaluated = proposal.update()
                    .observation(e.getMessage())
                    .approved(false)
                    .build();

            send(unevaluated);
        }
    }

    private void send(Proposal proposal) {
        template.convertAndSend(exchange, "", proposal);
    }

}
