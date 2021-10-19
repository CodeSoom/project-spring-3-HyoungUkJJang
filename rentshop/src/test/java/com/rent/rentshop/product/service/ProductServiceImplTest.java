package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductService 클래스")
@DataJpaTest
class ProductServiceImplTest {

    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Nested
    @DisplayName("register 메소드는")
    class Describe_register {

        @Nested
        @DisplayName("등록할 상품이 있을 경우에")
        class Context_exist_product {

            Product product;

            @BeforeEach
            void register_prepare() {
                product = Product.builder()
                        .productName("name1")
                        .productPrice(1000)
                        .productDescription("description")
                        .productImg("img1")
                        .build();
            }

            @Test
            @DisplayName("저장소에 상품을 저장 후 상품전용 DTO를 반환한다.")
            void It_save_return_product() {

                ProductRegisterForm result = productService.register(product);
                assertEquals(result.getProductName(), product.getProductName());

            }

        }

    }

}