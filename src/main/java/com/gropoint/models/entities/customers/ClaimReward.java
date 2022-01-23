package com.gropoint.models.entities.customers;

import com.gropoint.models.entities.principals.Reward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claim_reward")
public class ClaimReward implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reward reward;

    @ManyToOne
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClaim;

    private String status;
}
