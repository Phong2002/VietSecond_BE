package com.phenikaa.vietsecond.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "ResetPassword")
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "userId",nullable = false)
    private Integer userId;

    @Column(name = "token",nullable = false)
    private String token;
    public ResetPassword() {
    }


}
