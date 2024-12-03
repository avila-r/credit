package com.avila.proposals.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Table(name = "proposals")
@Entity public class Proposal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Id private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "proposal_deadline", nullable = false)
    private Integer deadline;

    @Column(name = "proposal_value", nullable = false, precision = 19, scale = 2)
    private BigDecimal value;

    @Column(name = "is_approved", nullable = false)
    private boolean approved;

    @Column(name = "is_integrated", nullable = false)
    private boolean notified;

    @Column(name = "proposal_observation", nullable = false)
    private String observation;
}
