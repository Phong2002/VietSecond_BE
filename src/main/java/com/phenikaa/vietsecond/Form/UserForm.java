package com.phenikaa.vietsecond.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phenikaa.vietsecond.Entity.Role;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserForm implements Serializable {
    private final int userId;
    @NotBlank(message = "first name must not be empty !")
    private final String firstName;
    @NotBlank(message = "last name must not be empty !")
    private final String lastName;
    private final String fullName;
    @NotBlank(message = "gender must not be empty !")
    private final String gender;
    @NotBlank(message = "address must not be empty !")
    private final String address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final Date dateOfBirth;
    @NotBlank(message = "phone number must not be empty !")
    private final String phoneNumber;
    @NotBlank(message = "email must not be empty !")
    private final String email;
    @NotBlank(message = "username must not be empty !")
    private final String username;
    @NotBlank(message = "password must not be empty !")
    @JsonIgnore
    private final String password;
    private final Role role;
}
