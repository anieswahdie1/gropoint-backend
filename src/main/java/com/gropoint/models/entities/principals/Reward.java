package com.gropoint.models.entities.principals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reward")
@SQLDelete(sql = "UPDATE reward SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Reward implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "ID Reward is required!")
    @Column(name = "ID_Reward", nullable = false, length = 10,unique = true)
    private String idReward;

    @NotEmpty(message = "Reward code is required!")
    @Column(name = "reward_code", nullable = false, unique = true, length = 10)
    private String rewardCode;

    @NotEmpty(message = "Reward name is required!")
    @Column(name = "reward_name", nullable = false, length = 45)
    private String rewardName;

    @NotEmpty(message = "Info is required!")
    @Column(name = "info")
    private String info;

    @NotEmpty(message = "Term and condition is required!")
    @Column(name = "term_condition")
    private String termAndCondition;

    //    @Temporal(TemporalType.DATE)
    private String startDate;

    //    @Temporal(TemporalType.DATE)
    private String endDate;

    private int budgetStock;
    private int redeemPoint;
    private String duration;
    private String status;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    private Merchant merchant;

    @ManyToOne
    private Principal principal;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Product product;
}
