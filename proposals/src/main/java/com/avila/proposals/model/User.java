package com.avila.proposals.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor
@Builder @Getter @Setter

@Table(name = "users")
@Entity public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Id private Long id;

    @Column(name = "user_cpf", unique = true, nullable = false, updatable = false)
    private String cpf;

    @Column(name = "user_number", unique = true, nullable = false)
    private String contact;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_surname", nullable = false)
    private String surname;

    @Column(name = "user_balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToOne(mappedBy = "user")
    private Proposal proposal;
}
