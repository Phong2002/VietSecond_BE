package com.phenikaa.vietsecond.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private  String fullName;
    private  String gender;
    private  String addressWard;
    private  String addressDistrict;
    private  String addressProvince;
    private  String phoneNumber;
    private  String avatar;

    public UserDto() {
    }
}
