package com.gropoint.models.repositories.customers;

import com.gropoint.models.entities.customers.Member;
import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.entities.principals.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MemberRepos extends JpaRepository<Member, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO member(name_member, principal_id, role_id, point)" +
            "VALUES(?1,?2,?3,?4)",nativeQuery = true)
    Integer saveNewMember(String nameMember, Principal principal, Role role, int point);

    @Modifying
    @Transactional
    @Query(value = "UPDATE member SET point=?1 WHERE id_member = ?2",nativeQuery = true)
    Integer updatePoint(int point, Long id);
}
