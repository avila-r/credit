package com.avila.credit.repository;

import com.avila.credit.model.Proposal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    List<Proposal> findAllByNotifiedIsFalse();
}
