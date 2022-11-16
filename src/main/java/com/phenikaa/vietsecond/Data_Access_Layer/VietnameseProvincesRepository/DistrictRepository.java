package com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, String> {
    List<District> findByProvinceCode(Province province);
    Optional<District> findById(String id);
}