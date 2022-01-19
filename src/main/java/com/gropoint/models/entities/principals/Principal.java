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
@Table(name = "principal")
@SQLDelete(sql = "UPDATE principal SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Principal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name principal is required!")
    @Column(name = "name_principal", nullable = false, length = 15)
    private String namePrincipal;

    @NotEmpty(message = "Password is required!")
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Role role;

    private boolean deleted = Boolean.FALSE;

}
