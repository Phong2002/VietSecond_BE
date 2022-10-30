package com.phenikaa.vietsecond.Presentation_Layer;
import com.phenikaa.vietsecond.Business_Logic_Layer.UserService;
import com.phenikaa.vietsecond.Form.FormLogin;
import com.phenikaa.vietsecond.Form.UserForm;
import com.phenikaa.vietsecond.Security.JWT.JwtTokenProvider;
import com.phenikaa.vietsecond.Security.JWT.ResponseJWT;
import com.phenikaa.vietsecond.Security.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> AuthenticateUser( @RequestBody FormLogin login) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateJwtToken( authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseJWT responseJWT = new ResponseJWT(jwt,userDetails.getUser());
        return new ResponseEntity<>(responseJWT, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody @Valid UserForm formUser) {
        userService.Register(formUser);
       return new ResponseEntity<String>("Tạo tài khoản thành công !",HttpStatus.CREATED);
    }
}
