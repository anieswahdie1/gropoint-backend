package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Category;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Long> {
    @Modifying
    @Query(value = "INSERT INTO category(name_category,deleted) VALUES(:nameCategory,:deleted)",nativeQuery = true)
    Integer saveNewCategory(String nameCategory, boolean deleted);

    @Modifying
    @Query(value = "UPDATE category SET name_category=:nameCategory WHERE id=:id",nativeQuery = true)
    Integer update(String nameCategory, Long id);

    @Query(value = "SELECT id,name_category FROM category WHERE deleted=false",nativeQuery = true)
    List<CustomField.getCategory> getAllCategory();
}
