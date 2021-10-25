package com.rent.rentshop.product.domain;

import com.rent.rentshop.common.BaseTime;
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
public class Product extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    private String productDescription;
    private int productPrice;
    private int deposit;
    private String productImg;

    @Builder
    public Product(Long id, String productName, String productDescription, int productPrice, int deposit, String productImg) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productImg = productImg;
    }


    public void updateProduct(String productName, String productDescription, int productPrice, int deposit, String productImg) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deposit = deposit;
        this.productImg = productImg;
    }

}
