package com.gropoint.dto;

import com.gropoint.models.entities.customers.Member;
import com.gropoint.models.entities.principals.Product;
import lombok.Data;

@Data
public class TransactionDTO {
    private Product product;
    private Member member;
    private int qtyProduct;
}
