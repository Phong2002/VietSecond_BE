package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Form.Filter.ProductFilterform;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.Response.MyProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    Page<MyProductResponse> getAllInventory(Pageable pageable, String search, ProductFilterform filter,String username);

    void deleteProduct(Integer productId);

    String[] checkListUrl(Integer productId);

    void createNewProduct(ProductForm form, MultipartFile[] images,String username);
}
