package com.gropoint.models.repositories.customers;

import com.gropoint.models.entities.customers.ReturnReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReturnRewardRepos extends JpaRepository<ReturnReward, Long> {
    @Modifying
    @Query(value = "INSERT INTO return_reward(return_date, status, claim_reward_id, member_id_member)" +
            "VALUES (:returnDate,:status,:claimReward,:member)",nativeQuery = true)
    Integer saveNewReturn(Date returnDate, String status, Long claimReward, Long member);
}
