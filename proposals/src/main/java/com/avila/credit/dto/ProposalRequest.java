package com.avila.credit.dto;

import com.avila.credit.model.Proposal;
import com.avila.credit.model.User;

import java.math.BigDecimal;

/**
 * Represents the request payload for a proposal, including user and proposal details.
 */
@lombok.Builder
public record ProposalRequest (
        String cpf,
        String name,
        String surname,
        BigDecimal balance,
        Integer deadline,
        BigDecimal value
) {
    /**
     * Converts a ProposalRequest DTO into a Proposal entity.
     *
     * @return a Proposal entity corresponding to the current ProposalRequest.
     */
    public Proposal entity() {
        // Use the builder pattern for creating the User entity
        User user = User.builder()
                .cpf(cpf)
                .name(name)
                .surname(surname)
                .balance(balance)
                .build(); // Build the User entity

        // Use the builder pattern for creating the Proposal entity
        return Proposal.builder()
                .user(user)  // Set the user entity created above
                .deadline(deadline)
                .value(value)
                .approved(false)
                .integrated(false)
                .observation("")
                .build();
    }
}