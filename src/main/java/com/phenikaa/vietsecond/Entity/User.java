package com.phenikaa.vietsecond.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phenikaa.vietsecond.Entity.Role;
import lombok.Data;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name="User" )

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false,unique=true)
    private Integer userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Formula(" concat(firstName, ' ', lastName) ")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name="dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "accountBalance")
    private Long accountBalance;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name =  "isActive")
    private boolean isActive;
}
