package com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.AdministrativeRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrativeRegionRepository extends JpaRepository<AdministrativeRegion, Integer> {
}