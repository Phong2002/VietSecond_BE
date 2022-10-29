package com.phenikaa.vietsecond.Security.JWT;

import com.phenikaa.vietsecond.Entity.Role;
import com.phenikaa.vietsecond.Entity.User;
import lombok.Data;

@Data
public class ResponseJWT {
    private String jwt;
    private String username;
    private String fullname;
    private Role role;

    public ResponseJWT(String jwt, User user) {
        this.jwt = jwt;
        this.username = user.getUsername();
        this.fullname = user.getFullName();
        this.role = user.getRole();
    }
}
