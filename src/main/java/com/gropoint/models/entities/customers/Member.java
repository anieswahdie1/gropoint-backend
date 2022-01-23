package com.gropoint.models.entities.customers;

import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.entities.principals.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMember;

    private String nameMember;

    @ManyToOne
    @JoinColumn(name = "principal_id",referencedColumnName = "id")
    private Principal principal;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    private int point;
}
