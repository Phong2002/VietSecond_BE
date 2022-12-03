package com.phenikaa.vietsecond.Form.Response;

import com.phenikaa.vietsecond.DTO.PostProductDto;
import com.phenikaa.vietsecond.Entity.ImageProduct;
import lombok.Data;

import java.util.List;

@Data
public class MyProductResponse {
    private Integer productId;
    private String productName;
    private String usageStatus;
    private String category;
    private String state;
    private List<ImageProduct> productImages;
    private PostProductDto postProductDto;


}
