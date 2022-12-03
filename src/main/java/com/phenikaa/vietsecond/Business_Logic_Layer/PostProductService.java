package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.AmazonClient;
import com.phenikaa.vietsecond.Business_Logic_Layer.VietnameseProvinces.IPostProductService;
import com.phenikaa.vietsecond.Data_Access_Layer.*;
import com.phenikaa.vietsecond.Data_Access_Layer.VietnameseProvincesRepository.WardRepository;
import com.phenikaa.vietsecond.Entity.*;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import com.phenikaa.vietsecond.Form.PostProductForm;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.UpdatePostProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PostProductService implements IPostProductService {
    @Autowired
    PostProductRepository postProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    ImageProductRepository imageProductRepository;
    @Override
    public void PostNewProduct(MultipartFile[] files, PostProductForm postProductForm, ProductForm productForm,String username) {
        User user = userRepository.findByUsername(username).get();

        Category category = categoryRepository.findById(productForm.getCategoryId()).get();
        Product product = new Product();
        product.setProductName(productForm.getProductName());
        product.setState(productForm.getState());
        product.setUsageStatus(productForm.getUsageStatus());
        product.setSeller(user);
        product.setCategory(category);
        Product productSave = productRepository.saveAndFlush(product);
        List<String> urlImgs =  amazonClient.uploadMultifile(files);
        List<ImageProduct> imageProducts = new ArrayList<>();
        for(String url:urlImgs){
            ImageProduct imageProduct  = new ImageProduct();
            imageProduct.setUrl(url);
            imageProduct.setProduct(productSave);
            imageProducts.add(imageProduct);
        }

        productSave.setProductImages(imageProducts);
        PostProduct postProduct = new PostProduct();
        postProduct.setTitle(postProductForm.getTitle());
        postProduct.setPostingTime(new Date());
        postProduct.setPrice(postProductForm.getPrice());
        postProduct.setAddressDetails(postProductForm.getAddressDetails());
        postProduct.setDescribe(postProductForm.getDescribe());
        Ward ward = wardRepository.findById(postProductForm.getAddressId()).get();
        postProduct.setAddress(ward);
        postProduct.setProduct(productSave);
        postProductRepository.save(postProduct);
    }

    @Override
    public void deletePostProduct(Integer id) {
        Product product = productRepository.findById(id).get();
        product.setState("NONE");
        product.getPostProduct().setProduct(null);
        postProductRepository.deleteById(product.getPostProduct().getId());
        product.setPostProduct(null);
        productRepository.save(product);
    }

    @Override
    public void updateInforPostProduct(UpdatePostProductForm form,MultipartFile[] files) {
        Product product = productRepository.findById(form.getProductId()).get();
        product.getPostProduct().getProduct().setProductName(form.getProductName());
        product.getPostProduct().setTitle(form.getPostTitle());
        product.getPostProduct().setDescribe(form.getPostDescribe());
        product.getPostProduct().setPrice(form.getPostPrice());

        List<ImageProduct> imageProducts = new ArrayList<>();
        if(files!=null){
            List<String> urlImgs =  amazonClient.uploadMultifile(files);
            for(String url:urlImgs){
                ImageProduct imageProduct  = new ImageProduct();
                imageProduct.setUrl(url);
                imageProduct.setProduct(product);
                imageProducts.add(imageProduct);
                imageProductRepository.save(imageProduct);
            }
        }
        postProductRepository.save(product.getPostProduct());
    }
}
