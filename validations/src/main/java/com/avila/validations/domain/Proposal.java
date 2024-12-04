package com.avila.validations.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Represents the response payload for a proposal, including its ID and details.
 */
@lombok.Builder
public record Proposal (
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
) {

    public ProposalBuilder update() {
        return Proposal.builder()
                .id(id)
                .userId(userId)
                .cpf(cpf)
                .contact(contact)
                .name(name)
                .surname(surname)
                .balance(balance)
                .deadline(deadline)
                .value(value)
                .observation(observation)
                .approved(approved);
    }

}
