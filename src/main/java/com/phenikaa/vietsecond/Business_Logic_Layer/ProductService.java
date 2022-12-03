package com.phenikaa.vietsecond.Business_Logic_Layer;
import com.phenikaa.vietsecond.Business_Logic_Layer.Specification.ProductSpecification;
import com.phenikaa.vietsecond.DTO.PostProductDto;
import com.phenikaa.vietsecond.Data_Access_Layer.*;
import com.phenikaa.vietsecond.Entity.Category;
import com.phenikaa.vietsecond.Entity.ImageProduct;
import com.phenikaa.vietsecond.Entity.Product;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.Filter.ProductFilterform;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.Response.MyProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostProductRepository postProductRepository;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    UserRepository userRepository;


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageProductRepository imageProductRepository;

    @Override
    public Page<MyProductResponse> getAllInventory(Pageable pageable, String search, ProductFilterform filter,String username) {
        Specification<Product> where = null;

        ProductSpecification findSeller = new ProductSpecification("username", "=", username);
        where = Specification.where(findSeller);

        if (!StringUtils.isEmpty(search)) {
            ProductSpecification searchByNameProdcut = new ProductSpecification("productName", "%LIKE%", search);
            where = where.and(searchByNameProdcut);
        }

        if(filter != null && filter.getUsageStatus() != null){
            ProductSpecification filterByUsageStatus = new ProductSpecification("usageStatus","=", filter.getUsageStatus());
            if(where==null){
                where = Specification.where(filterByUsageStatus);
            }
            else{
                where = where.and(filterByUsageStatus);
            }
        }

        if(filter != null && filter.getState() != null){
            ProductSpecification filterByStatus = new ProductSpecification("state","=",filter.getState());
            if(where==null){
                where = Specification.where(filterByStatus);
            }
            else{
                where = where.and(filterByStatus);
            }
        }
        User seller =  userRepository.findByUsername(username).get();
        Page<Product> productPage = productRepository.findAll(where,pageable);
        Page<MyProductResponse> myProductResponsePage = productPage.map(new Function<Product, MyProductResponse>() {
            @Override
            public MyProductResponse apply(Product product) {
                MyProductResponse myProductResponse = new MyProductResponse();
                myProductResponse.setProductId(product.getId());
                myProductResponse.setProductName(product.getProductName());
                myProductResponse.setProductImages(product.getProductImages());
                myProductResponse.setState(product.getState());
                myProductResponse.setUsageStatus(product.getUsageStatus());
                myProductResponse.setCategory(product.getCategory().getCategoryName());
                PostProductDto postProductDto = new PostProductDto();
                if(product.getPostProduct()!=null){
                    postProductDto.setId(product.getPostProduct().getId());
                    postProductDto.setDescribe(product.getPostProduct().getDescribe());
                    postProductDto.setPrice(product.getPostProduct().getPrice());
                    postProductDto.setTitle(product.getPostProduct().getTitle());
                    postProductDto.setPostingTime(product.getPostProduct().getPostingTime());
                    postProductDto.setAddressDetails(product.getPostProduct().getAddressDetails());
                    postProductDto.setWardAddress(product.getPostProduct().getAddress().getFullName());
                    postProductDto.setDistrictAddress(product.getPostProduct().getAddress().getDistrictCode().getFullName());
                    postProductDto.setProvinceAddress(product.getPostProduct().getAddress().getDistrictCode().getProvinceCode().getFullName());
                    myProductResponse.setPostProductDto(postProductDto);
                }
                return myProductResponse;
            }
        });
                return myProductResponsePage;
    }

    @Override
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).get();
        for(ImageProduct img:product.getProductImages()){
            amazonClient.deleteFile(img.getUrl());
        }
        if(product.getPostProduct()!=null){
            postProductRepository.deleteById(
                    product.getPostProduct().getId());
        }
        else{
            productRepository.deleteById(productId);
        }
    }

    @Override
    public String[] checkListUrl(Integer productId) {
        Product product = productRepository.findById(productId).get();
        return product.getProductImages().stream().map(value->value.getUrl()).toArray(String[]::new);

    }

    @Override
    public void createNewProduct(ProductForm form, MultipartFile[] images, String username) {
        User user = userRepository.findByUsername(username).get();
        Category category = categoryRepository.findById(form.getCategoryId()).get();
        Product product = new Product();
        product.setProductName(form.getProductName());
        product.setState("NONE");
        product.setUsageStatus(form.getUsageStatus());
        product.setSeller(user);
        product.setCategory(category);
        Product productSave = productRepository.saveAndFlush(product);
        List<String> urlImgs =  amazonClient.uploadMultifile(images);
        for(String url:urlImgs){
            ImageProduct imageProduct  = new ImageProduct();
            imageProduct.setUrl(url);
            imageProduct.setProduct(productSave);
            imageProductRepository.save(imageProduct);
        }

    }
}
