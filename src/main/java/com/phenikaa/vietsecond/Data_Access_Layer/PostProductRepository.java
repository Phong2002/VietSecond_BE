package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Entity.PostProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostProductRepository extends JpaRepository<PostProduct, Integer>, JpaSpecificationExecutor<PostProduct> {
}