package com.gropoint.dto;

import lombok.Data;

@Data
public class PrincipalDTO {
    private String namePrincipal;
    private String password;
    private Long role;
}
