package com.phenikaa.vietsecond.Business_Logic_Layer.Specification;

import com.phenikaa.vietsecond.Entity.PostProduct;
import com.phenikaa.vietsecond.Entity.Product;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class ProductSpecification implements Specification<Product> {

    private String field;
    private String operator;
    private Object value;
    private String className;

    public ProductSpecification(String field, String operator, Object value,String className) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.className = className;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<User, Product> seller = root.join("seller");

        if(className.equalsIgnoreCase("seller")){
            if(operator.equalsIgnoreCase("%LIKE%")){
                return criteriaBuilder.like(seller.get(field),"%"+value.toString()+"%");
            }
            else if(operator.equalsIgnoreCase("=")){
                return criteriaBuilder.equal(seller.get(field),value.toString());
            }
            else if(operator.equalsIgnoreCase(">=")){
                if (value instanceof Date) {
                    return criteriaBuilder.greaterThanOrEqualTo(seller.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.greaterThanOrEqualTo(seller.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(seller.get(field), value.toString());
                }
            }
            else if(operator.equalsIgnoreCase("<=")){
                if (value instanceof Date) {
                    return criteriaBuilder.lessThanOrEqualTo(seller.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.lessThanOrEqualTo(seller.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(seller.get(field), value.toString());
                }
            }
            else if (operator.equalsIgnoreCase("!")){
                return criteriaBuilder.notEqual(seller.get(field), value.toString());
            }
            else
                return null;
        }

        else if(className.equalsIgnoreCase("postProduct")){
            Join<PostProduct,Product> postProduct = root.join("postProduct");
            if(operator.equalsIgnoreCase("%LIKE%")){
                return criteriaBuilder.like(postProduct.get(field),"%"+value.toString()+"%");
            }
            else if(operator.equalsIgnoreCase("=")){
                return criteriaBuilder.equal(postProduct.get(field),value.toString());
            }
            else if(operator.equalsIgnoreCase(">=")){
                if (value instanceof Date) {
                    return criteriaBuilder.greaterThanOrEqualTo(postProduct.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.greaterThanOrEqualTo(postProduct.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(postProduct.get(field), value.toString());
                }
            }
            else if(operator.equalsIgnoreCase("<=")){
                if (value instanceof Date) {
                    return criteriaBuilder.lessThanOrEqualTo(postProduct.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.lessThanOrEqualTo(postProduct.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(postProduct.get(field), value.toString());
                }
            }
            else if (operator.equalsIgnoreCase("!")){
                return criteriaBuilder.notEqual(postProduct.get(field), value.toString());
            }
        }

        else if(className.equalsIgnoreCase("address")){
            Join<PostProduct,Product> postProduct = root.join("postProduct");
            Join<Ward,PostProduct> address = postProduct.join("address");
            if(operator.equalsIgnoreCase("%LIKE%")){
                return criteriaBuilder.like(address.get(field),"%"+value.toString()+"%");
            }
            else if(operator.equalsIgnoreCase("=")){
                return criteriaBuilder.equal(address.get(field),value.toString());
            }
            else if(operator.equalsIgnoreCase(">=")){
                if (value instanceof Date) {
                    return criteriaBuilder.greaterThanOrEqualTo(address.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.greaterThanOrEqualTo(address.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(address.get(field), value.toString());
                }
            }
            else if(operator.equalsIgnoreCase("<=")){
                if (value instanceof Date) {
                    return criteriaBuilder.lessThanOrEqualTo(address.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.lessThanOrEqualTo(address.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(address.get(field), value.toString());
                }
            }
            else if (operator.equalsIgnoreCase("!")){
                return criteriaBuilder.notEqual(address.get(field), value.toString());
            }
        }

        else if(className.equalsIgnoreCase("product")) {
            if(operator.equalsIgnoreCase("%LIKE%")){
                return criteriaBuilder.like(root.get(field),"%"+value.toString()+"%");
            }
            else if(operator.equalsIgnoreCase("=")){
                return criteriaBuilder.equal(root.get(field),value.toString());
            }
            else if(operator.equalsIgnoreCase(">=")){
                if (value instanceof Date) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(field), value.toString());
                }
            }
            else if(operator.equalsIgnoreCase("<=")){
                if (value instanceof Date) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(field), (Date) value);
                }
                else if(value instanceof Number){
                    return criteriaBuilder.lessThanOrEqualTo(root.get(field), (Long) value);
                }
                else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(field), value.toString());
                }
            }
            else if (operator.equalsIgnoreCase("!")){
                return criteriaBuilder.notEqual(root.get(field), value.toString());
            }
        }
        return null;
    }
}
