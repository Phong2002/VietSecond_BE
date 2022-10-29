package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import com.phenikaa.vietsecond.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> getAllUser() {
        return null;
    }

    @Override
    public Boolean ExistsByUsername(String username) {
        return null;
    }

    @Override
    public User findByUserId(Integer userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return null;
    }
}
