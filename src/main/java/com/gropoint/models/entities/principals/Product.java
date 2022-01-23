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
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_product",nullable = false,length = 15)
    private String idProduct;

    @NotEmpty(message = "Product name is required!")
    @Column(name = "name_product", nullable = false,length = 45)
    private String nameProduct;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    private Double qtyStock;
    private Double currStock;
    private Double price;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Principal principal;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Program program;

    private boolean deleted = Boolean.FALSE;

}
