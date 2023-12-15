package com.api.picpay.model;

import com.api.picpay.dto.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String CPF;

    @Column(unique = true)
    private String email;

    private String password;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
