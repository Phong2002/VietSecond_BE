package com.phenikaa.vietsecond.Entity.VietnameseProvinces;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wards")
@Data
@AllArgsConstructor
public class Ward {
    @Id
    @Column(name = "code", nullable = false, length = 20)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "district_code")
    private District districtCode;

    public Ward() {
    }
}