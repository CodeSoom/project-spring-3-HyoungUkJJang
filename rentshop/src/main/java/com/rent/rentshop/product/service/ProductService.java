package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.dto.ProductSimpleResponseDto;
import com.rent.rentshop.product.dto.ResponseData;

import java.util.List;

/**
 * 상품 저장, 조회, 수정, 삭제기능을 제공하는 서비스
 */
public interface ProductService {

    /**
     * 상품 전체를 조회해 상품목록을 리턴합니다.
     * @return 상품 리스트
     */
    List<Product> getProducts();

    /**
     * 상품을 저장소에 저장하고 저장한 상품을 리턴합니다.
     * @param form 상품 정보
     * @return 저장된 상품
     */
    ProductRegisterForm register(Product form);

}
