package com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;

import java.util.List;

public interface IDistrictService {
    List<District> findByProvinceId(String id);
}
