package com.avila.proposals.service;

import com.avila.proposals.dto.ProposalRequest;
import com.avila.proposals.exception.users.UserAlreadyExistsException;
import com.avila.proposals.model.Proposal;
import com.avila.proposals.repository.ProposalRepository;
import com.avila.proposals.repository.UserRepository;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service public class ProposalService {
    private final UserRepository users;
    private final ProposalRepository proposals;
    private final NotificationService notifier;

    @Transactional public Proposal create(ProposalRequest request) {
        if (users.existsByCpf(request.cpf()))
            throw new UserAlreadyExistsException("CPF " + request.cpf() + " already in use");

        if (users.existsByContact(request.contact()))
            throw new UserAlreadyExistsException("Contact + " + request.contact() + " already in use");

        Proposal proposal = proposals.save(request.entity());
        boolean ok = notifier.notify(proposal);
        if (!ok) {
            proposal.setNotified(false);
            return proposals.save(proposal);
        }
        return proposal;
    }

    @Transactional public List<Proposal> list() {
        return StreamSupport.stream(proposals.findAll().spliterator(), false)
                .toList();
    }

    @Transactional public void remove(Long id) {
        proposals.deleteById(id);
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    void notifyProposals() {
        for (Proposal proposal : proposals.findAllByNotifiedIsFalse()) {
            boolean ok = notifier.notify(proposal);
            if (!ok) {
                return;
            }

            proposal.setNotified(true);
            proposals.save(proposal);
        }
    }

}
