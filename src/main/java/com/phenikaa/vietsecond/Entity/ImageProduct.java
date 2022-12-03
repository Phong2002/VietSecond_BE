package com.phenikaa.vietsecond.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@Entity
@Table(name = "image_product")
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_product_id", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "url", nullable = false)
    private String url;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

    public ImageProduct() {
    }
}