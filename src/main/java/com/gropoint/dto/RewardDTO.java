package com.gropoint.dto;

import com.gropoint.models.entities.principals.Category;
import com.gropoint.models.entities.principals.Merchant;
import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.entities.principals.Product;
import lombok.Data;

@Data
public class RewardDTO {
    private String idReward;
    private String rewardName;
    private String rewardCode;
    private String info;
    private String termAndCondition;
    private int budgetStock;
    private int redeemPoint;
    private String duration;
    private String status;
    private String startDate;
    private String endDate;
    private Principal principal;
    private Category category;
    private Product product;
    private Merchant merchant;
}
