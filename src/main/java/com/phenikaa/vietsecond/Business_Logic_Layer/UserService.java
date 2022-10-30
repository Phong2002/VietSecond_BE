package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.UserForm;
import com.phenikaa.vietsecond.Security.Service.UserDetailsImpl;
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
        return null;
    }

    @Override
    public User FindByUserId(Integer userId) {
        return null;
    }

    @Override
    public void Register(UserForm userForm) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
        return new UserDetailsImpl(user);
    }
}
