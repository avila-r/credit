package com.avila.notifications.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents the response payload for a proposal, including its ID and details.
 */
@lombok.Builder
public record ProposalResponse (
        Long id,
        @JsonProperty("user_id") Long userId,
        String cpf,
        String contact,
        String name,
        String surname,
        BigDecimal balance,
        Integer deadline,
        BigDecimal value,
        String observation,
        boolean approved
){}
