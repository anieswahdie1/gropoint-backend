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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
@SQLDelete(sql = "UPDATE warehouse SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "ID Warehouse is required!")
    @Column(name = "id_warehouse",nullable = false,length = 10)
    private String idWarehouse;

    @NotEmpty(message = "Name Warehouse isq required!")
    @Column(name = "name_warehouse",nullable = false,length = 45)
    private String nameWarehouse;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Principal principal;

    private boolean deleted = Boolean.FALSE;

}
