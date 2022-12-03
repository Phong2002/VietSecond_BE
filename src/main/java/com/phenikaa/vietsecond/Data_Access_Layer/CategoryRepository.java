package com.phenikaa.vietsecond.Data_Access_Layer;

import com.phenikaa.vietsecond.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findByParentId(String parentId);
    Optional<Category> findById(Integer id);
}
