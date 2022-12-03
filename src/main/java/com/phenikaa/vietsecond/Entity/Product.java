package com.phenikaa.vietsecond.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "usage_status")
    private String usageStatus;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne()
    @JoinColumn(name = "seller", nullable = false)
    private User seller;

    @Column(name = "state")
    private String state;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL})
    List<ImageProduct> productImages;

    @OneToOne(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PostProduct postProduct;
    public Product() {
    }

}