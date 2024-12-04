package com.avila.validations.service.strategy.impl;

import com.avila.validations.domain.Proposal;
import com.avila.validations.exceptions.LowScoreException;
import com.avila.validations.service.strategy.CreditScoring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component class ScoreMockStrategy implements CreditScoring {

    @Value("${sms.observations.low-score}")
    private String LOW_SCORE_ERROR_MESSAGE;

    @Override
    public int validate(Proposal proposal) {
        int score = new Random().nextInt(0, 1000);

        if (score <= 200)
            throw new LowScoreException(String.format(LOW_SCORE_ERROR_MESSAGE, proposal.name()));

        return score <= 400
                ? 150
                : score <= 600
                ? 180
                : 220;
    }
}
