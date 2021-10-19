package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductRegisterForm;
import com.rent.rentshop.product.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
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
    @DisplayName("getProducts 메소드는")
    class Describe_getProducts {

        @Nested
        @DisplayName("저장소에 상품들이 존재하면")
        class Context_exist_products {

            int productSize;

            @BeforeEach
            void prepare() {
                for (int i = 0; i < 5; i++) {
                  Product product = Product.builder()
                            .productName("name"+i)
                            .productPrice(1000*i)
                            .productDescription("description"+i)
                            .productImg("img"+i)
                            .build();
                    productService.register(product);
                }
                productSize = productService.getProducts().size();
            }

            @Test
            @DisplayName("상품 리스트를 리턴합니다.")
            void It_return_products() {

                assertEquals(productSize, productService.getProducts().size());

            }
        }

        @Nested
        @DisplayName("저장소에 상품들이 존재하지 않다면")
        class Context_not_exist_products {

            @BeforeEach
            void prepare() {
                productRepository.findAll().clear();
            }

            @Test
            @DisplayName("비어있는 리스트를 반환합니다.")
            void It_return_empty_list() {

                assertThat(productService.getProducts()).isEmpty();

            }

        }

        @AfterEach
        void cleanUp() {
            productRepository.findAll().clear();
        }

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

        @AfterEach
        void cleanUp() {
            productRepository.findAll().clear();
        }

    }

}