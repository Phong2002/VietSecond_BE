package com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.AdministrativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, Integer> {
}