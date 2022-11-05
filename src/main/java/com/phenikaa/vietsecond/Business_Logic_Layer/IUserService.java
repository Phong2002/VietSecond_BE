package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.UserForm;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IUserService extends UserDetailsService {
    Page<User> GetAllUser();
    Boolean ExistsByUsername(String username);
    User FindByUserId(Integer userId);
    Boolean ExistsByEmail(String email);
    Boolean ExistsByPhoneNumber(String phoneNumber);
    void Register(UserForm userForm)throws MessagingException, UnsupportedEncodingException;;
    User getByEmail(String email);
    void updateNewPassword(String token, String newPassword);
    void resetPassword(String email) throws MessagingException, UnsupportedEncodingException;
    void accountVerification(String token);

}
