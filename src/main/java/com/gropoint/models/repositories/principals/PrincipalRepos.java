package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Principal;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrincipalRepos extends JpaRepository<Principal, Long> {
    @Modifying
    @Query(value = "INSERT INTO principal(name_principal, username, password, role_id, deleted) " +
            "VALUES(:namePrincipal,:username,:password,:role,:deleted)",nativeQuery = true)
    Integer saveNewPricipal(String namePrincipal, String username, String password, Long role, boolean deleted);

    @Modifying
    @Query(value = "UPDATE principal SET name_principal=:namePrincipal, password=:password," +
            "username=:username,role_id=:role WHERE id=:id",nativeQuery = true)
    Integer update(String namePrincipal, String password, String username, Long role, Long id);

    @Query(value = "SELECT id, name_principal,username,role_id FROM principal WHERE deleted = false",nativeQuery = true)
    List<CustomField.getPrincipal> getAllPrincipal();

    @Query(value = "SELECT id, username, name_principal, role_id FROM principal WHERE deleted = false AND id=:id",nativeQuery = true)
    CustomField.getPrincipal getOnePrincipal(Long id);

    @Query(value = "SELECT id, principal_name, username, role_id " +
            "FROM principal " +
            "WHERE username = ?1 AND password=?2",nativeQuery = true)
    Optional<CustomField.getLogin> getDataByUsrPass(String username, boolean password);

    @Query(value = "SELECT id, principal_name, username, role_id " +
            "FROM principal " +
            "WHERE username = ?1",nativeQuery = true)
    Optional<CustomField.getLogin> getDataByUsr(String username);

    @Query(value = "select password from principal where username = ?1",nativeQuery = true)
    Optional<CustomField.getPassword> getPasswordByUsername(String username);
}
