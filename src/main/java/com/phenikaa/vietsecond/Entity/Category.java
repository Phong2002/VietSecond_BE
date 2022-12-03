package com.phenikaa.vietsecond.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    public Category() {
    }

    @Column(name ="category_name")
    private String categoryName;
    @Column(name ="parent_id")
    private String parentId;

    @OneToMany(mappedBy = "parentId")
    List<Category> categories;

    @Column(name="path")
    private String path;

    @Column(name = "level")
    private String level;

}
