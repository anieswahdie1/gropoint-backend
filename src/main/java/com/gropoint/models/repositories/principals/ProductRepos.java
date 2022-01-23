package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Product;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {
    @Modifying
    @Query(value = "INSERT INTO product(id_product,name_product,curr_stock,category_id,warehouse_id,price, principal_id, deleted) " +
            "VALUES(:idProduct,:nameProduct,:currStock,:category,:warehouse,:price,:principal ,:deleted)",nativeQuery = true)
    Integer saveNewProduct(String idProduct, String nameProduct, Double currStock,
                           Long category, Long warehouse,Double price, Long principal, boolean deleted);

    @Modifying
    @Query(value = "UPDATE product SET name_product=:nameProduct, curr_stock=:currStock, " +
            " category_id=:category, warehouse_id=:warehouse,price=:price " +
            "WHERE id=:id ",nativeQuery = true)
    Integer update(String nameProduct, Double currStock, Long category, Long warehouse, Double price, Long id);

    @Query(value = "SELECT p.id, p.id_product, p.name_product, c.name_category,\n" +
            "p.qty_stock, p.curr_stock,p.warehouse_id, p.category_id,\n" +
            "p.principal_id, pr.name_principal, w.name_warehouse, " +
            " p.price \n" +
            "FROM product p\n" +
            "JOIN category c ON p.category_id = c.id  \n" +
            "JOIN principal pr ON p.principal_id = pr.id\n" +
            "JOIN warehouse w ON p.warehouse_id = w.id \n" +
            "WHERE p.deleted=false AND p.principal_id = ?1",nativeQuery = true)
    Iterable<CustomField.getProduct> getProductByPrincipalId(Long id);

    @Query(value = "SELECT p.id, p.id_product, p.name_product, c.name_category,\n" +
            "p.qty_stock, p.curr_stock, p.warehouse_id, p.category_id,\n" +
            "p.principal_id, pr.name_principal, w.name_warehouse, " +
            " p.price \n" +
            "FROM product p\n" +
            "JOIN category c ON p.category_id = c.id  \n" +
            "JOIN principal pr ON p.principal_id = pr.id\n" +
            "JOIN warehouse w ON p.warehouse_id = w.id \n" +
            "WHERE p.deleted=false AND p.id = ?1",nativeQuery = true)
    CustomField.getProduct getOneProductById(Long id);

    @Query(value = "SELECT pro.id, pro.id_product, pro.name_product, pro.curr_stock, \n" +
            "pro.qty_stock, cat.name_category, ware.name_warehouse \n" +
            "FROM product pro \n" +
            "JOIN category cat ON pro.category_id = cat.id\n" +
            "JOIN warehouse ware ON pro.warehouse_id = ware.id\n" +
            "WHERE pro.deleted = false " +
            "AND pro.category_id = ?1 AND pro.principal_id = ?2", nativeQuery = true)
    Iterable<CustomField.getProductByCategory> getProductByCategory(Long categoryId, Long principalId);

    @Query(value = "SELECT id, name_product \n" +
            "FROM product \n" +
            "WHERE program_id IS NULL", nativeQuery = true)
    Iterable<CustomField.getProductDropdown> getDropdownProduct();

    @Query(value = "SELECT p.* \n" +
            "FROM product p \n" +
            "WHERE p.deleted=false AND p.id = ?1 ",nativeQuery = true)
    Product GetProductByID(Long id);

}
