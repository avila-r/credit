package com.avila.credit.repository;

import com.avila.credit.model.Proposal;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    Optional<Proposal> findByUser_Id(Long userId);
    Optional<Proposal> findByUser_Cpf(String userCpf);
}
