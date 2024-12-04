package com.avila.validations.service.strategy.impl;

import com.avila.validations.domain.Proposal;
import com.avila.validations.service.strategy.CreditScoring;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component class ActiveLoansEvaluationStrategy implements CreditScoring {
    @Override
    public int validate(Proposal proposal) {
        return hasActiveLoans(proposal) ? 0 : 80;
    }

    // Mock
    private boolean hasActiveLoans(Proposal ignored) {
        return new Random().nextBoolean();
    }
}
