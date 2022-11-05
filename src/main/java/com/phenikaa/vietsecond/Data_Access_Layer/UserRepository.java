package com.phenikaa.vietsecond.Data_Access_Layer;
import com.phenikaa.vietsecond.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);
    User findByUserId(Integer userId);
    User findByEmail(String email);
}
