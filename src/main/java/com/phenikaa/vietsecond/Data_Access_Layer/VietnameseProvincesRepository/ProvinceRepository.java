package com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, String> {
    @Override
    Optional<Province> findById(String id);
}