package com.phenikaa.vietsecond.Form;

import lombok.Data;

@Data
public class UpdatePostProductForm {
    private Integer productId;
    private String productName;
    private String postTitle;
    private String postDescribe;
    private Long postPrice;
    private String[] deleteUrlImageList;
}
