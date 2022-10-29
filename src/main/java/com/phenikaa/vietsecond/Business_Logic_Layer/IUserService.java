package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    Page<User> getAllUser();
    Boolean ExistsByUsername(String username);
    User findByUserId(Integer userId);
}
