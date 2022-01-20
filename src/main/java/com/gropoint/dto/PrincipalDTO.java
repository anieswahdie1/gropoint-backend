package com.gropoint.dto;

import lombok.Data;

@Data
public class PrincipalDTO {
    private Long id;
    private String namePrincipal;
    private String username;
    private String password;
    private Long role;
}
