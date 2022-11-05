package com.phenikaa.vietsecond.Presentation_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.IUserService;
import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    UserRepository userRepository;



    @GetMapping("/verification/{token}")
    public ResponseEntity<?> accountVerification(@PathVariable(name = "token") String token){
        userService.accountVerification(token);
        return new ResponseEntity<String>("Verification successfully", HttpStatus.OK);
    }

    @PostMapping("reset-password/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable(name = "email") String email) throws MessagingException, UnsupportedEncodingException {
        userService.resetPassword(email);
        return new ResponseEntity<String>("",HttpStatus.CREATED);
    }

    @PutMapping("reset-password/{token}")
    public ResponseEntity<?> updateNewPassword(@PathVariable(name="token") String token,
                                               @RequestBody String newPassword ){
        userService.updateNewPassword(token,newPassword);
        return new ResponseEntity<String>("Update new password successfully",HttpStatus.OK);
    }
}
