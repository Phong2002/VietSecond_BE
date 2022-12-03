package com.phenikaa.vietsecond.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "account_verification")
public class AccountVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id",nullable = false)
    private Integer userId;

    @Column(name = "token",nullable = false)
    private String token;
    public AccountVerification() {
    }


    public AccountVerification(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
