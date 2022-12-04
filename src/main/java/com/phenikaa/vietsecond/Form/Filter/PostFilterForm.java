package com.phenikaa.vietsecond.Form.Filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PostFilterForm {
    private Long minPrice;
    private Long maxPrice;
    private String addressId;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDateTime;
}
