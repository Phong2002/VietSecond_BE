package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer>, JpaSpecificationExecutor<ImageProduct> {
    ImageProduct findByUrl(String urls);
    List<ImageProduct> findAllByUrlIn(String[]url);
}