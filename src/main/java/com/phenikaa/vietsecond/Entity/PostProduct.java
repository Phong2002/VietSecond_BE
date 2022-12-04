package com.phenikaa.vietsecond.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phenikaa.vietsecond.Entity.VietnameseProvinces.Ward;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "post_product")
public class PostProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_product_id", nullable = false)
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "title")
    private String title;

    @Column(name = "`describe`")
    private String describe;

    @Column(name = "price")
    private Long price;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "address", nullable = false)
    private Ward address;

    @Column(name = "address_details")
    private String addressDetails;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posting_time", nullable = false)
    private Date postingTime;
    public PostProduct() {
    }

}