package com.avila.credit.repository;

import com.avila.credit.model.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
