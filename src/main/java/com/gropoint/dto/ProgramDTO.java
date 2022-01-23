package com.gropoint.dto;

import com.gropoint.models.entities.principals.Principal;
import lombok.Data;

@Data
public class ProgramDTO {
    private Long id;
    private String idProgram;
    private String name;
    private String description;
    private String status;
    private String startDate;
    private String endDate;
    private int point;
    private boolean repeats;
    private boolean multiple;
    private int amountTransaction;
    private int amountUsage;
    private String duration;
    private Principal principal;
    private int productId;
}
