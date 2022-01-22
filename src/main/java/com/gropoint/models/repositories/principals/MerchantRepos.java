package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Merchant;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepos extends JpaRepository<Merchant, Long> {
    @Modifying
    @Query(value = "INSERT INTO merchant(id_merchant,name_merchant,name_city,lattitude,longitude,principal_id, deleted) " +
            "VALUES(:idMerchant,:nameMerchant,:nameCity,:lattitude,:longitude,:principal,:deleted)",nativeQuery = true)
    Integer saveMerchant(String idMerchant, String nameMerchant, String nameCity,
                         String lattitude, String longitude, Long principal, boolean deleted);

    @Modifying
    @Query(value = "UPDATE merchant SET name_merchant=:nameMerchant, name_city=:nameCity," +
            "lattitude=:lattitude,longitude=:longitude " +
            "WHERE id=:id",nativeQuery = true)
    Integer updateMerchant(String nameMerchant, String nameCity, String lattitude, String longitude, Long id);

    @Query(value = "SELECT id,id_merchant,name_merchant,name_city,lattitude,longitude,principal_id " +
            "FROM merchant WHERE id=:id AND deleted=false",nativeQuery = true)
    CustomField.getMerchant getMerchantByID(Long id);

    @Query(value = "SELECT id,id_merchant,name_merchant,name_city,lattitude,longitude,principal_id " +
            "FROM merchant WHERE principal_id=:principal AND deleted=false",nativeQuery = true)
    List<CustomField.getMerchant> getMerchantByPrincipalID(Long principal);
}
