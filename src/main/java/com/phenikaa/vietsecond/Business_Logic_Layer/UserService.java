package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.UserForm;
import com.phenikaa.vietsecond.Security.Service.UserDetailsImpl;
import com.phenikaa.vietsecond.Utils.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

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
    public void Register(UserForm userForm) {
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

        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
        return new UserDetailsImpl(user);
    }
}
