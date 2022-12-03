package com.phenikaa.vietsecond.Presentation_Layer;


import com.phenikaa.vietsecond.Data_Access_Layer.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@PathVariable(name="id")  String id){
        if(id.equals("0")){
            id=null;
        }
        return new ResponseEntity<>(categoryRepository.findByParentId(id), HttpStatus.OK);
    }
}
