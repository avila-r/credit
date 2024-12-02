package com.avila.credit.dto;

import com.avila.credit.model.Proposal;

import java.math.BigDecimal;

/**
 * Represents the response payload for a proposal, including its ID and details.
 */
@lombok.Builder
public record ProposalResponse (
        Long id,
        String cpf,
        String name,
        String surname,
        BigDecimal balance,
        Integer deadline,
        BigDecimal value
) {
    /**
     * Converts a Proposal enti into a ProposalResponse DTO.
     *
     * @param proposal the Proposal entity to be converted.
     * @return a ProposalResponse DTO representing the proposal details.
     */
    public static ProposalResponse from(Proposal proposal) {
        return ProposalResponse.builder()
                .id(proposal.getId())
                .cpf(proposal.getUser().getCpf())
                .name(proposal.getUser().getName())
                .surname(proposal.getUser().getSurname())
                .balance(proposal.getUser().getBalance())
                .deadline(proposal.getDeadline())
                .value(proposal.getValue())
                .build();
    }
}
