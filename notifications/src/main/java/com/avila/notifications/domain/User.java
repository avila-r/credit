package com.avila.notifications.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder @Getter @Setter
public class User {
    private Long id;
    private String cpf;
    private String name;
    private String surname;
    private BigDecimal balance;
}
