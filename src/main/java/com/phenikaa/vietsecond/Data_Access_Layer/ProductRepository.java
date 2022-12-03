package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Business_Logic_Layer.Specification.ProductSpecification;
import com.phenikaa.vietsecond.Entity.Product;
import com.phenikaa.vietsecond.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

}