package com.avila.proposals.dto;

import com.avila.proposals.model.Proposal;
import com.avila.proposals.model.User;
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
                .contact(proposal.getUser().getContact())
                .name(proposal.getUser().getName())
                .surname(proposal.getUser().getSurname())
                .balance(proposal.getUser().getBalance())
                .deadline(proposal.getDeadline())
                .value(proposal.getValue())
                .observation(proposal.getObservation())
                .approved(proposal.isApproved())
                .build();
    }

    /**
     * Converts a ProposalResponse DTO into a Proposal entity.
     *
     * @return a Proposal entity representing the details in the DTO.
     */
    public Proposal entity() {
        User user = User.builder()
                .id(this.userId())
                .cpf(this.cpf())
                .contact(this.contact())
                .name(this.name())
                .surname(this.surname())
                .balance(this.balance())
                .build();

        return Proposal.builder()
                .id(this.id())
                .user(user)
                .deadline(this.deadline())
                .value(this.value())
                .observation(this.observation())
                .approved(this.approved())
                .notified(true)
                .build();
    }
}
