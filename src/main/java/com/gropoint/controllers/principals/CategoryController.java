package com.gropoint.controllers.principals;

import com.gropoint.dto.CategoryDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/category/add")
    public ResponseEntity<CommonResponse> addNewCategory(@RequestBody CategoryDTO payload){
        return categoryService.saveCategory(payload);
    }

    @PutMapping("/category/edit")
    public ResponseEntity<CommonResponse> editCategory(@RequestBody CategoryDTO payload){
        return categoryService.saveCategory(payload);
    }

    @GetMapping("/categories")
    public ResponseEntity<CommonResponse> getAllCategory(){
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/category/delete/{id}")
    public void deleteCategoryByID(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
}
