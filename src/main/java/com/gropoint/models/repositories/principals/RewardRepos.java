package com.gropoint.models.repositories.principals;

import com.gropoint.models.entities.principals.Reward;
import com.gropoint.responses.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepos extends JpaRepository<Reward, Long> {
    @Query(value = "SELECT rw.id, rw.id_reward, rw.reward_code, rw.reward_name, rw.redeem_point, rw.duration,\n" +
            "rw.budget_stock, rw.start_date, rw.end_date, rw.info, rw.term_condition, ct.name_category,\n" +
            "mer.name_merchant, rw.principal_id, pro.name_product, rw.status " +
            "FROM reward rw JOIN category ct ON rw.category_id = ct.id\n" +
            "JOIN merchant mer ON rw.merchant_id = mer.id\n" +
            "JOIN product pro ON rw.product_id = pro.id " +
            "WHERE rw.deleted = false AND rw.principal_id = ?1", nativeQuery = true)
    Iterable<CustomField.getReward> getRewardByPrincipalId(Long id);

    @Query(value = "SELECT rw.id, rw.id_reward, rw.reward_code, rw.reward_name, rw.redeem_point, rw.duration,\n" +
            "rw.budget_stock, rw.start_date, rw.end_date, rw.info, rw.term_condition, rw.category_id, ct.name_category,\n" +
            "rw.merchant_id, mer.name_merchant, rw.principal_id, rw.product_id, pro.name_product, rw.status " +
            "FROM reward rw " +
            "JOIN category ct ON rw.category_id = ct.id\n" +
            "JOIN merchant mer ON rw.merchant_id = mer.id\n" +
            "JOIN product pro ON rw.product_id = pro.id " +
            "WHERE rw.deleted = false AND rw.id = ?1", nativeQuery = true)
    CustomField.getReward getOneRewardById(Long id);
}
