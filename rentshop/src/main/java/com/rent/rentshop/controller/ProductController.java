package com.rent.rentshop.controller;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.dto.ProductSimpleResponseDto;
import com.rent.rentshop.product.dto.ResponseData;
import com.rent.rentshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품과 관련된 HTTP 요청을 처리하는 클래스
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 전체를 조회하여 상품목록을 반환 후 200 상태코드를 반환합니다.
     * @return 상품목록 DTO
     */
    @GetMapping(value = "/rent/products")
    public ResponseData getProducts() {

        List<ProductSimpleResponseDto> products = productService.getProducts()
                .stream()
                .map(p -> new ProductSimpleResponseDto(
                        p.getId(),
                        p.getProductName(),
                        p.getProductPrice()))
                .collect(Collectors.toList());

        return new ResponseData(products);

    }

    /**
     * 상품을 등록하고 등록된 상품정보와 201 상태코드를 반환합니다.
     * @param form 상품 정보
     * @return 등록된 상품
     */
    @PostMapping(value = "/rent/product")
    public ProductRegisterForm register(@RequestBody @Valid ProductRegisterForm form) {

        Product product = Product.builder()
                .productName(form.getProductName())
                .productDescription(form.getProductDescription())
                .productPrice(form.getProductPrice())
                .productImg(form.getProductImg())
                .build();

        ProductRegisterForm responseProduct = productService.register(product);
        return responseProduct;
    }

}
