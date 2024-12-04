package com.avila.validations.service.strategy.impl;

import com.avila.validations.domain.Proposal;
import com.avila.validations.service.strategy.CreditScoring;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component class CreditAffordabilityStrategy implements CreditScoring {

    @Override
    public int validate(Proposal proposal) {
        return hasCreditAffordability(proposal) ? 100 : 0;
    }

    private boolean hasCreditAffordability(Proposal proposal) {
        BigDecimal value = proposal.value();

        return proposal.balance()
                .compareTo(value) > 0;
    }
}
