package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.UserForm;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    Page<User> GetAllUser();
    Boolean ExistsByUsername(String username);
    User FindByUserId(Integer userId);
    void Register(UserForm userForm);
}
