package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.dto.ProductSimpleResponseDto;
import com.rent.rentshop.product.dto.ResponseData;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

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
