package com.gropoint.dto;

import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.entities.principals.Role;
import lombok.Data;

@Data
public class MemberDTO {
    private String nameMember;
    private Principal principal;
    private Role role;
}
