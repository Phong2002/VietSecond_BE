package com.phenikaa.vietsecond.Business_Logic_Layer;
import com.phenikaa.vietsecond.Business_Logic_Layer.Specification.ProductSpecification;
import com.phenikaa.vietsecond.DTO.PostProductDto;
import com.phenikaa.vietsecond.DTO.UserDto;
import com.phenikaa.vietsecond.Data_Access_Layer.*;
import com.phenikaa.vietsecond.Entity.Category;
import com.phenikaa.vietsecond.Entity.ImageProduct;
import com.phenikaa.vietsecond.Entity.Product;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.Filter.PostFilterForm;
import com.phenikaa.vietsecond.Form.Filter.ProductFilterform;
import com.phenikaa.vietsecond.Form.ProductForm;
import com.phenikaa.vietsecond.Form.Response.MyProductResponse;
import com.phenikaa.vietsecond.Form.Response.ProductResponse;
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
        ProductSpecification findSeller = new ProductSpecification("username", "=",username,"seller");
        where = Specification.where(findSeller);
        if (!StringUtils.isEmpty(search)) {
            ProductSpecification searchByNameProdcut = new ProductSpecification("productName", "%LIKE%", search,"product");
            if(where==null){
                where = Specification.where(searchByNameProdcut);
            }
            else
            where = where.and(searchByNameProdcut);
        }
        if(filter != null && filter.getUsageStatus() != null){
            ProductSpecification filterByUsageStatus = new ProductSpecification("usageStatus","=", filter.getUsageStatus(),"product");
            if(where==null){
                where = Specification.where(filterByUsageStatus);
            }
            else{
                where = where.and(filterByUsageStatus);
            }
        }

        if(filter != null && filter.getState() != null){
            ProductSpecification filterByStatus = new ProductSpecification("state","=",filter.getState(),"product");
            if(where==null){
                where = Specification.where(filterByStatus);
            }
            else{
                where = where.and(filterByStatus);
            }
        }

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

    @Override
    public Page<ProductResponse> getAllProductPost(Pageable pageable, String search, ProductFilterform productFilter, PostFilterForm postFilter, String username) {
        Specification<Product> where = null;
        ProductSpecification findSeller = new ProductSpecification("username", "!",username,"seller");
        where = Specification.where(findSeller);
        if (!StringUtils.isEmpty(search)) {
            ProductSpecification searchByNameProdcut = new ProductSpecification("productName", "%LIKE%", search,"product");
            if(where==null){
                where = Specification.where(searchByNameProdcut);
            }
            else
                where = where.and(searchByNameProdcut);
        }
        if(productFilter != null && productFilter.getUsageStatus() != null){
            ProductSpecification filterByUsageStatus = new ProductSpecification("usageStatus","=", productFilter.getUsageStatus(),"product");
            if(where==null){
                where = Specification.where(filterByUsageStatus);
            }
            else{
                where = where.and(filterByUsageStatus);
            }
        }

        if(productFilter != null && productFilter.getState() != null){
            ProductSpecification filterByStatus = new ProductSpecification("state","=",productFilter.getState(),"product");
            if(where==null){
                where = Specification.where(filterByStatus);
            }
            else{
                where = where.and(filterByStatus);
            }
        }


            ProductSpecification checkIsPost = new ProductSpecification("id","!",0,"postProduct");
            if(where==null){
                where = Specification.where(checkIsPost);
            }
            else{
                where = where.and(checkIsPost);
            }


        if(postFilter != null && postFilter.getMinPrice() != null){
            ProductSpecification filterByMinPrice = new ProductSpecification("price",">=",postFilter.getMinPrice(),"postProduct");
            if(where==null){
                where = Specification.where(filterByMinPrice);
            }
            else{
                where = where.and(filterByMinPrice);
            }
        }

        if(postFilter != null && postFilter.getMaxPrice() != null){
            ProductSpecification filterByMaxPrice = new ProductSpecification("price","<=",postFilter.getMaxPrice(),"postProduct");
            if(where==null){
                where = Specification.where(filterByMaxPrice);
            }
            else{
                where = where.and(filterByMaxPrice);
            }
        }


        if(postFilter != null && postFilter.getStartDateTime() != null){
            ProductSpecification filterByMinDateTime = new ProductSpecification("postingTime",">=",postFilter.getStartDateTime(),"postProduct");
            if(where==null){
                where = Specification.where(filterByMinDateTime);
            }
            else{
                where = where.and(filterByMinDateTime);
            }
        }

        if(postFilter != null && postFilter.getEndDateTime() != null){
            ProductSpecification filterByMaxDateTime = new ProductSpecification("postingTime","<=",postFilter.getEndDateTime(),"postProduct");
            if(where==null){
                where = Specification.where(filterByMaxDateTime);
            }
            else{
                where = where.and(filterByMaxDateTime);
            }
        }

        if(postFilter != null && postFilter.getAddressId() != null){
            ProductSpecification filterByAddress = new ProductSpecification("postingTime","<=",postFilter.getEndDateTime(),"address");
            if(where==null){
                where = Specification.where(filterByAddress);
            }
            else{
                where = where.and(filterByAddress);
            }
        }

        Page<Product> productPage = productRepository.findAll(where,pageable);

        Page<ProductResponse> productResponsePage = productPage.map(new Function<Product, ProductResponse>() {
            @Override
            public ProductResponse apply(Product product) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setProductId(product.getId());
                productResponse.setProductName(product.getProductName());
                productResponse.setProductImages(product.getProductImages());
                productResponse.setState(product.getState());
                productResponse.setUsageStatus(product.getUsageStatus());
                productResponse.setCategory(product.getCategory().getCategoryName());

                UserDto userDto = new UserDto();
                userDto.setFullName(product.getSeller().getFullName());
                userDto.setAvatar(product.getSeller().getAvatar());
                userDto.setGender(product.getSeller().getGender());
                userDto.setPhoneNumber(product.getSeller().getPhoneNumber());
                userDto.setAddressWard(product.getSeller().getAddress().getName());
                userDto.setAddressDistrict(product.getSeller().getAddress().getDistrictCode().getName());
                userDto.setAddressProvince(product.getSeller().getAddress().getDistrictCode().getProvinceCode().getName());

                productResponse.setSeller(userDto);

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
                    productResponse.setPostProductDto(postProductDto);
                }
                return productResponse;
            }
        });
        return productResponsePage;
    }
}
