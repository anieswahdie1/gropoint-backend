package com.gropoint.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String idProduct;
    private String nameProduct;
    private Long category;
    private Double qtyStock;
    private Long principal;
    private Double price;
    private Long warehouse;
}
