package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Role;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
    @Modifying
    @Query(value = "INSERT INTO role(role_name,deleted) value(:roleName,:deleted)",nativeQuery = true)
    int saveNewRole(String roleName, boolean deleted);

    @Query(value = "SELECT id, role_name FROM role WHERE deleted = false",nativeQuery = true)
    List<CustomField.getRole> allRoles();

    @Query(value = "SELECT id, role_name FROM role WHERE deleted = false AND id=:id",nativeQuery = true)
    CustomField.getRole roleByID(Long id);
}
