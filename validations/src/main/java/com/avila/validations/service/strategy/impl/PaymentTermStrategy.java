package com.avila.validations.service.strategy.impl;

import com.avila.validations.domain.Proposal;
import com.avila.validations.exceptions.InvalidDeadlineException;
import com.avila.validations.service.strategy.CreditScoring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component class PaymentTermStrategy implements CreditScoring {

    @Value("${sms.observations.invalid-deadline}")
    public String DEADLINE_ERROR_MESSAGE;

    @Override
    public int validate(Proposal proposal) {
        return score(proposal);
    }

    private int score(Proposal proposal) {
        if (proposal.deadline() <= 0)
            throw new InvalidDeadlineException(String.format(DEADLINE_ERROR_MESSAGE, proposal.name()));

        return Math.max (0, Math.min (
                (int) (((double) (120 - proposal.deadline()) / 120) * 100), 100)
        );
    }
}
