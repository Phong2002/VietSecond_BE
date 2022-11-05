package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Integer> {
    ResetPassword findByToken(String token);
    Boolean existsByUserId(Integer userId);
}
