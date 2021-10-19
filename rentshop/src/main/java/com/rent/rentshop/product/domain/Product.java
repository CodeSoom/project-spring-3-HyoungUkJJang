package com.rent.rentshop.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 상품의 메인 도메인
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
