package com.avila.proposals.dto;

import com.avila.proposals.model.Proposal;
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
        String name,
        String surname,
        BigDecimal balance,
        Integer deadline,
        BigDecimal value
) {
    /**
     * Converts a Proposal entity into a ProposalResponse DTO.
     *
     * @param proposal the Proposal entity to be converted.
     * @return a ProposalResponse DTO representing the proposal details.
     */
    public static ProposalResponse from(Proposal proposal) {
        return ProposalResponse.builder()
                .id(proposal.getId())
                .userId(proposal.getUser().getId())
                .cpf(proposal.getUser().getCpf())
                .name(proposal.getUser().getName())
                .surname(proposal.getUser().getSurname())
                .balance(proposal.getUser().getBalance())
                .deadline(proposal.getDeadline())
                .value(proposal.getValue())
                .build();
    }
}
