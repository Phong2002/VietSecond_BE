package com.phenikaa.vietsecond.Presentation_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.ProductService;
import com.phenikaa.vietsecond.Data_Access_Layer.ProductRepository;
import com.phenikaa.vietsecond.Form.Filter.ProductFilterform;
import com.phenikaa.vietsecond.Form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/inventory")
    public ResponseEntity<?> getAllInventory(Authentication authentication,Pageable pageable, @RequestParam(required = false) String search, ProductFilterform filter){
        String username = authentication.getName();
        return new ResponseEntity<>(productService.getAllInventory(pageable,search,filter,username), HttpStatus.OK);
    }

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") Integer productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<String>("Delete success",HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createNewProduct(Authentication authentication, @RequestPart MultipartFile[] files, ProductForm form){
        String username = authentication.getName();
        productService.createNewProduct(form,files,username);
        return new ResponseEntity<String>("Create success",HttpStatus.OK);
    }

}
