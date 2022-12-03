package com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces;

import com.phenikaa.vietsecond.Entity.PostProduct;
import com.phenikaa.vietsecond.Form.PostProductForm;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.UpdatePostProductForm;
import org.springframework.web.multipart.MultipartFile;

public interface IPostProductService {

    public void PostNewProduct(MultipartFile[] files, PostProductForm postProduct, ProductForm productForm,String username);
    public void deletePostProduct(Integer id);
    public void updateInforPostProduct(UpdatePostProductForm form,MultipartFile[] files);
}
