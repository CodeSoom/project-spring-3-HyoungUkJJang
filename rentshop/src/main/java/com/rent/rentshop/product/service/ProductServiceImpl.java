package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdateForm;
import com.rent.rentshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product register(Product form) {

        Product registerProduct = productRepository.save(form);
        return registerProduct;

    }

    @Override
    public Product getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        return product;

    }

    @Override
    @Transactional
    public void update(Long id, ProductUpdateForm form) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

        product.updateProduct(
                form.getProductName(),
                form.getProductDescription(),
                form.getProductPrice(),
                form.getProductImg());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        productRepository.delete(findProduct);
    }

}
