package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductRegisterForm register(Product form) {

        Product registerProduct = productRepository.save(form);
        return responseConvertForm(registerProduct);

    }

    protected ProductRegisterForm responseConvertForm(Product product) {

        return ProductRegisterForm.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .productImg(product.getProductImg())
                .build();

    }

}
