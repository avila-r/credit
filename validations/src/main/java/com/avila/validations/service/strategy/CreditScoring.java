package com.avila.validations.service.strategy;

import com.avila.validations.domain.Proposal;

public interface CreditScoring {
    int validate(Proposal proposal);
}
