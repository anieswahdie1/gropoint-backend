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
@Table(name = "merchant")
@SQLDelete(sql = "UPDATE merchant SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Merchant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idMerchant;

    @NotEmpty(message = "Name merchant is required!")
    @Column(name = "name_merchant", nullable = false,length = 50)
    private String nameMerchant;

    @NotEmpty(message = "City name is required!")
    @Column(name = "name_city", nullable = false, length = 100)
    private String nameCity;

    private String lattitude;
    private String longitude;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Principal principal;

    private boolean deleted = Boolean.FALSE;

}
