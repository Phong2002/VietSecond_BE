package com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces;

import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.DistrictRepository;
import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.ProvinceRepository;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DistrictService implements IDistrictService{
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    DistrictRepository districtRepository;


    @Override
    public List<District> findByProvinceId(String id) {
        Optional<Province> province = provinceRepository.findById(id);
        return districtRepository.findByProvinceCode(province.get());
    }
}
