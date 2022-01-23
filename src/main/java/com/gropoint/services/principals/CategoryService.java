package com.gropoint.services.principals;

import com.gropoint.dto.CategoryDTO;
import com.gropoint.models.entities.principals.Category;
import com.gropoint.models.repositories.principals.CategoryRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.CustomField;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepos categoryRepos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;


    public ResponseEntity<CommonResponse> saveCategory(CategoryDTO payload){
        Category category = modelMapper.map(payload,Category.class);

        if (category.getId() != null){
            category.setNameCategory(payload.getNameCategory());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            categoryRepos.update(category.getNameCategory(), category.getId()),
                            "Success update category by ID."
                    ));
        } else {
            category.setNameCategory(payload.getNameCategory());
            category.setDeleted(false);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            categoryRepos.saveNewCategory(category.getNameCategory(), category.isDeleted()),
                            "Success saved new category."
                    ));
        }
    }

    public ResponseEntity<CommonResponse> findAllCategories(){
        List<CustomField.getCategory> listCategory = categoryRepos.getAllCategory();

        if (!listCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(listCategory,"Success get all categories."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("There is no categories found."));
        }
    }

    public void delete(Long id){
        categoryRepos.deleteById(id);
    }
}
