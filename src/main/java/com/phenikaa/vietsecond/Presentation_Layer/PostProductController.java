package com.phenikaa.vietsecond.Presentation_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.IImageProductService;
import com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces.IPostProductService;
import com.phenikaa.vietsecond.Entity.PostProduct;
import com.phenikaa.vietsecond.Form.PostProductForm;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.UpdatePostProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/post-product")
@CrossOrigin("*")
public class PostProductController {

    @Autowired
    IPostProductService postProductService;

    @Autowired
    IImageProductService imageProductService;

    @PostMapping("/new-product")
    public ResponseEntity<?> postNewProduct(Authentication authentication,@RequestPart MultipartFile[] files, PostProductForm postProductForm, ProductForm productForm){
        String username = authentication.getName();
        postProductService.PostNewProduct(files,postProductForm,productForm,username);
        return new ResponseEntity<String>("Post product succesfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deletePostProduct(@PathVariable(name="productId") Integer productId){
        postProductService.deletePostProduct(productId);
        return new ResponseEntity<String>("Delete successfully",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInforPostProduct(@RequestPart(value = "files", required=false) MultipartFile[] files, UpdatePostProductForm form){
        imageProductService.deleteImages(form.getDeleteUrlImageList());
        postProductService.updateInforPostProduct(form,files);
        return new ResponseEntity<>("update success",HttpStatus.OK);
    }
}
