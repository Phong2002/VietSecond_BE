package com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces;

import com.phenikaa.vietsecond.Entity.VietnameseProvinces.District;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;

import java.util.List;

public interface IWardService {
    List<Ward> findByDistrictId(String id);
}
