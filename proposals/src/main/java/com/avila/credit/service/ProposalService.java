package com.avila.credit.service;

import com.avila.credit.dto.ProposalRequest;
import com.avila.credit.exception.users.UserAlreadyExistsException;
import com.avila.credit.model.Proposal;
import com.avila.credit.repository.*;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service public class ProposalService {
    private final UserRepository users;
    private final ProposalRepository proposals;

    @Transactional public Proposal create(ProposalRequest request) {
        if (users.existsByCpf(request.cpf()))
            throw new UserAlreadyExistsException("CPF " + request.cpf() + " already in use");

        return proposals.save(request.entity());
    }

    @Transactional public List<Proposal> list() {
        return StreamSupport.stream(proposals.findAll().spliterator(), false)
                .toList();
    }

}
