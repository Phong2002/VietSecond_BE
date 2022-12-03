package com.phenikaa.vietsecond.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class PostProductDto implements Serializable {
    private  Integer id;
    private  String title;
    private  String describe;
    private  Long price;
    private  Date postingTime;
    private  String provinceAddress;
    private  String districtAddress;
    private  String wardAddress;
    private String addressDetails;

    public PostProductDto() {
    }
}