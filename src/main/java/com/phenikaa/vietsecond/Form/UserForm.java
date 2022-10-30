package com.phenikaa.vietsecond.Form;

import com.phenikaa.vietsecond.Entity.Role;
import com.phenikaa.vietsecond.Entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "email is not valid")
    private final String email;
    @NotBlank(message = "username must not be empty !")
    private final String username;
    @NotBlank(message = "password must not be empty !")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.,/;#?!@$%^&*-]).{8,}$" ,
            message = "password has minimum eight characters, at least one letter, one number and one special character")
    private final String password;
    private final Role role;

    public User ToEntity(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setAccountBalance(0L);
        user.setDateOfBirth(dateOfBirth);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        return user;
    }
}
