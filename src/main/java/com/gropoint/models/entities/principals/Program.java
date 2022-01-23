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
@Table(name="program")
@SQLDelete(sql = "UPDATE program SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Program implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_program", nullable = false, unique = true, length = 10)
    private String idProgram;

    @NotEmpty(message = "Name program is required!")
    @Column(name = "name_program", nullable = false,length = 50)
    private String name;

    @NotEmpty(message = "Description is required!")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message = "Status is required!")
    @Column(name = "status", nullable = false, length = 10)
    private String status;

    //    @Temporal(TemporalType.DATE)
    private String startDate;
    private String endDate;

    private int point;
    private boolean repeats;
    private boolean multiple;
    private int amountTransaction;
    private int amountUsage;
    private String duration;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "principal_id",referencedColumnName = "id")
    private Principal principal;

    private Long productId;
}
