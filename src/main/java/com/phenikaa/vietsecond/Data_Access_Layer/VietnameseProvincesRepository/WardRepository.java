package com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {
    List<Ward> findByDistrictCode(District district);
}