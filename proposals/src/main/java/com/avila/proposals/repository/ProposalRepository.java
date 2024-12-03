package com.avila.proposals.repository;

import com.avila.proposals.model.Proposal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    List<Proposal> findAllByNotifiedIsFalse();
}
