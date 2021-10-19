package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.dto.ProductUpdateForm;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Product getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        return product;

    }

    @Override
    public void update(Long id, ProductUpdateForm form) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

        product.updateProduct(
                form.getProductName(),
                form.getProductDescription(),
                form.getProductPrice(),
                form.getProductImg());

    }

    @Override
    public void delete(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        productRepository.delete(findProduct);
    }

    private ProductRegisterForm responseConvertForm(Product product) {

        return ProductRegisterForm.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .productImg(product.getProductImg())
                .build();

    }

}
