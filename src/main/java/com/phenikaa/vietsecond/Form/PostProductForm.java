package com.phenikaa.vietsecond.Form;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostProductForm implements Serializable {
    private final String title;
    private final String describe;
    private final Long price;
    private final String addressId;
    private final String addressDetails;
}
