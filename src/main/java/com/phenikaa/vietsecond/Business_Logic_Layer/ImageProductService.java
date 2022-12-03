package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.ImageProductRepository;
import com.phenikaa.vietsecond.Entity.ImageProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageProductService implements IImageProductService{
    @Autowired
    AmazonClient amazonClient;

    @Autowired
    ImageProductRepository imageProductRepository;


    @Override
    public void deleteImages(String[] urls) {
        if(urls==null){
            return ;
        }
        for(String url:urls){
            amazonClient.deleteFile(url);
        }

        List<ImageProduct> imageProductsDelete = imageProductRepository.findAllByUrlIn(urls);
        for(ImageProduct img:imageProductsDelete){
            img.getProduct().setProductImages(null);
            img.setProduct(null);
            imageProductRepository.save(img);
            imageProductRepository.deleteById(img.getId());
        }

    }



}
