package com.phenikaa.vietsecond.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import lombok.Data;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name="user" )

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false,unique=true)
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Formula(" concat(first_name, ' ', last_name) ")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "address")
    private Ward address;

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account_balance")
    private Long accountBalance;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name= "avatar")
    private String avatar;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name =  "isActive")
    private boolean isActive;
}
