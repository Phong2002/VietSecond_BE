package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.AccountVerificationRepository;
import com.phenikaa.vietsecond.Data_Access_Layer.ResetPasswordRepository;
import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import com.phenikaa.vietsecond.Entity.AccountVerification;
import com.phenikaa.vietsecond.Entity.ResetPassword;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.UserForm;
import com.phenikaa.vietsecond.Security.Authentication;
import com.phenikaa.vietsecond.Security.Service.UserDetailsImpl;
import com.phenikaa.vietsecond.Utils.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    Authentication authentication;

    @Autowired
    AccountVerificationRepository accountVerificationRepository;

    @Autowired
    IEmailService emailService;

    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Override
    public Page<User> GetAllUser() {
        return null;
    }

    @Override
    public Boolean ExistsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public User FindByUserId(Integer userId) {

        return null;
    }

    @Override
    public Boolean ExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean ExistsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void Register(UserForm userForm) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.existsByEmail(userForm.getEmail())){
            throw new CustomException(400,"email is exists!");
        }
        if(userRepository.existsByUsername(userForm.getUsername())){
            throw new CustomException(400,"username is exists!");
        }
        if(userRepository.existsByPhoneNumber(userForm.getPhoneNumber())){
            throw new CustomException(400,"phone number is exists!");
        }
        User user = userForm.ToEntity();
        String passwordEn = authentication.passwordEncoder().encode(user.getPassword());
        user.setPassword(passwordEn);
        userRepository.save(user);

        final String newToken = UUID.randomUUID().toString();
        Optional<User> userRegister = userRepository.findByUsername(user.getUsername());

        AccountVerification accountVerification = new AccountVerification(userRegister.get().getUserId(), newToken);
        accountVerificationRepository.save(accountVerification);
        emailService.sendEmailAccountVerification(user,newToken);
    }

    @Override
    public void accountVerification(String token) {
        if(!accountVerificationRepository.existsByToken(token)){
            throw new CustomException(400,"this token is not exists");
        }
        AccountVerification accountVerification = accountVerificationRepository.findByToken(token);
        Integer userId = accountVerification.getUserId();
        User user = userRepository.findByUserId(userId);
        user.setActive(true);
        userRepository.save(user);
        accountVerificationRepository.deleteByToken(token);
    }

    @Override
    public void resetPassword(String email) throws MessagingException, UnsupportedEncodingException {
        if(!userRepository.existsByEmail(email)){
            throw new CustomException(400,"Email is not exists");
        }
        User user = userRepository.findByEmail(email);

        if(resetPasswordRepository.existsByUserId(user.getUserId())){
            throw new CustomException(400,"This user requested to reset the password");
        }
        String token = UUID.randomUUID().toString();
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setUserId(user.getUserId());
        resetPassword.setToken(token);
        resetPasswordRepository.save(resetPassword);
        emailService.sendEmailResetPassword(user,token);
    }

    @Override
    public void updateNewPassword(String token, String newPassword) {
        ResetPassword resetPassword = resetPasswordRepository.findByToken(token);
        if(resetPassword==null){
            throw new CustomException(400,"Token is not exists");
        }
        String password = authentication.passwordEncoder().encode(newPassword);
        User user = userRepository.findByUserId(resetPassword.getUserId());
        user.setPassword(password);
        resetPasswordRepository.delete(resetPassword);
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
        return new UserDetailsImpl(user);
    }
}
