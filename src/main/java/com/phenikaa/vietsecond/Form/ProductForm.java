package com.phenikaa.vietsecond.Form;

import com.phenikaa.vietsecond.Entity.Product;
import lombok.Data;

@Data
public class ProductForm {
    private final Integer categoryId;
    private final String usageStatus;
    private final String productName;
    private final String state;
}
