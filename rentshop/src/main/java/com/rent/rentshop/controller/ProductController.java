package com.rent.rentshop.controller;

import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/rent/product")
    public ProductRegisterForm register(@RequestBody @Valid ProductRegisterForm form) {
        ProductRegisterForm product = productService.register(form);
        return product;
    }
}
