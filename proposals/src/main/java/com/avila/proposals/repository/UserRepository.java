package com.avila.proposals.repository;

import com.avila.proposals.model.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByContact(String contact);
    boolean existsByCpf(String cpf);
}
