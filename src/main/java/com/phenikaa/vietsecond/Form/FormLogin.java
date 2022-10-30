package com.phenikaa.vietsecond.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FormLogin {
    @NotBlank(message = "user name must not be empty !")
    private String username;
    @NotBlank(message = "user name must not be empty !")
    private String password;
}
