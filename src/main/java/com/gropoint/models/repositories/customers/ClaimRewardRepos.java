package com.gropoint.models.repositories.customers;

import com.gropoint.models.entities.customers.ClaimReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ClaimRewardRepos extends JpaRepository<ClaimReward, Long> {
    @Modifying
    @Query(value = "INSERT INTO claim_reward(member_id_member, reward_id, date_claim, status) " +
            "VALUES(?1,?2,?3,?4)",nativeQuery = true)
    Integer saveClaimReward(Long member, Long reward, Date dateClaim, String status);

    @Modifying
    @Query(value = "UPDATE claim_reward SET status=:status WHERE id=:id",nativeQuery = true)
    Integer updateStatus(String status, Long id);
}
