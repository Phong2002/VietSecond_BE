package com.phenikaa.vietsecond.Business_Logic_Layer.Specification;

import com.phenikaa.vietsecond.Entity.Product;
import com.phenikaa.vietsecond.Entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private String field;
    private String operator;
    private String value;

    public ProductSpecification(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<User, Product> seller = root.join("seller");
        if(operator.equalsIgnoreCase("%LIKE%")){
            return criteriaBuilder.like(root.get(field),"%"+value+"%");
        }
        else if(operator.equalsIgnoreCase("=")){
            if(field=="username"){
                return criteriaBuilder.equal(seller.get(field),value);
            }
            return criteriaBuilder.equal(root.get(field),value);
        }
        return null;
    }
}
