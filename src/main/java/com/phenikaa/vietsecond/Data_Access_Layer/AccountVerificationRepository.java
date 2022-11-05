package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Entity.AccountVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification,Integer> {
    Boolean existsByToken(String token);
    AccountVerification findByToken(String token);
    AccountVerification findByUserId(Integer userId);
    void deleteByToken(String token);
}
