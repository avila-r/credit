package com.avila.validations.service.strategy.impl;

import com.avila.validations.domain.Proposal;
import com.avila.validations.exceptions.BlacklistedException;
import com.avila.validations.service.strategy.CreditScoring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component class BlacklistCheckStrategy implements CreditScoring {

    @Value("${sms.observations.blacklisted}")
    public String BLACKLISTED_ERROR_MESSAGE;

    @Override
    public int validate(Proposal proposal) {
        if (isBlacklisted(proposal)) {
            throw new BlacklistedException(String.format(BLACKLISTED_ERROR_MESSAGE, proposal.name()));
        }

        return 100;
    }

    // Mock
    private boolean isBlacklisted(Proposal ignored) {
        return new Random().nextBoolean();
    }
}
