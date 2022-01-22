package com.gropoint.dto;

import lombok.Data;

@Data
public class MerchantDTO {
    private Long id;
    private String idMerchant;
    private String nameMerchant;
    private String nameCity;
    private String lattitude;
    private String longitude;
    private Long principal;
}
