package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Warehouse;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepos extends JpaRepository<Warehouse, Long> {
    @Modifying
    @Query(value = "INSERT INTO warehouse(id_warehouse,name_warehouse,principal_id,deleted) " +
            "VALUES(:idWarehouse,:nameWarehouse,:principal,:deleted)",nativeQuery = true)
    Integer saveWarehouse(String idWarehouse, String nameWarehouse, Long principal, boolean deleted);

    @Modifying
    @Query(value = "UPDATE warehouse SET name_warehouse=:nameWarehouse WHERE id=:id",nativeQuery = true)
    Integer update(String nameWarehouse, Long id);

    @Query(value = "SELECT id,id_warehouse, name_warehouse FROM warehouse " +
            "WHERE deleted=false AND principal_id=:principal",nativeQuery = true)
    List<CustomField.getWarehouse> getWarehouseByPrincipalID(Long principal);

    @Query(value = "SELECT id,id_warehouse, name_warehouse FROM warehouse " +
            "WHERE deleted=false AND id=:id",nativeQuery = true)
    CustomField.getWarehouse getWarehouseByID(Long id);
}
