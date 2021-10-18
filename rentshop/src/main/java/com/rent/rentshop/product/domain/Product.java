package com.rent.rentshop.product.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    private String productDescription;
    private int productPrice;
    private String productImg;

    @Builder
    public Product(String productName, String productDescription, int productPrice, String productImg) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

}
