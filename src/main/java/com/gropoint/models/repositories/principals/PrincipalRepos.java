package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.entities.principals.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepos extends JpaRepository<Principal, Long> {
    @Modifying
    @Query(value = "INSERT INTO principal(name_principal, password, role_id, deleted) VALUES(:namePrincipal,:password,:role,:deleted)",nativeQuery = true)
    Integer saveNewPricipal(String namePrincipal, String password, Long role, boolean deleted);
}
