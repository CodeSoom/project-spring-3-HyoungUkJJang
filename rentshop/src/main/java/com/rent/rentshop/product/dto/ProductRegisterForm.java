package com.rent.rentshop.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 상품등록 전용 폼
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRegisterForm {

    @NotEmpty(message = "상품 이름은 필수입니다.")
    private String productName;
    @NotNull(message = "상품 가격은 필수입니다.")
    private int productPrice;

    private String productDescription;
    private String productImg;

    @Builder
    public ProductRegisterForm(String productName, int productPrice, String productDescription, String productImg) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productImg = productImg;
    }

}
