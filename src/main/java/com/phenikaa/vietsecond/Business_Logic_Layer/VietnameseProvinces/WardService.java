package com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces;

import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.DistrictRepository;
import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.WardRepository;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WardService implements IWardService{
    @Autowired
    WardRepository wardRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<Ward> findByDistrictId(String id) {
        Optional<District> district = districtRepository.findById(id);
        return wardRepository.findByDistrictCode(district.get());
    }
}
