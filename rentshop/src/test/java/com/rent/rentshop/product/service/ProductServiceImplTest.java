package com.rent.rentshop.product.service;

import com.rent.rentshop.error.ProductNotFoundException;
import com.rent.rentshop.product.domain.Product;
import com.rent.rentshop.product.dto.ProductUpdateForm;
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
    @DisplayName("getProduct 메소드는")
    class Describe_getProduct {

        @Nested
        @DisplayName("상세 조회할 상품이 있을 경우에")
        class Context_detail_getProduct {

            Long productId;
            Product form;

            @BeforeEach
            void prepare() {

                form = createProduct();

                Product product = productService.register(form);
                productId = product.getId();

            }

            @Test
            @DisplayName("상품의 아이디를 통해 상품을 찾아 리턴한다.")
            void It_return_product() {

                Product result = productService.getProduct(productId);
                assertEquals(form.getProductName(),result.getProductName());

            }

        }

        @Nested
        @DisplayName("상세 조회할 상품을 찾지 못할 경우에")
        class Context_not_exist_product {

            Long invalidId;

            @BeforeEach
            void prepare() {
                invalidId = productService.getProducts().size()+9999L;
                productService.getProducts().clear();
            }

            @Test
            @DisplayName("ProductNotFoundException 예외를 던진다.")
            void It_return_productNotFoundException() {

                assertThatThrownBy(() -> productService.getProduct(invalidId))
                        .isInstanceOf(ProductNotFoundException.class);

            }

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
                product = createProduct();
            }

            @Test
            @DisplayName("저장소에 상품을 저장 후 상품전용 DTO를 반환한다.")
            void It_save_return_product() {
                Product result = productService.register(product);
                assertEquals(result.getProductName(), product.getProductName());
            }

        }

        @AfterEach
        void cleanUp() {
            productRepository.findAll().clear();
        }

    }

    @Nested
    @DisplayName("update 메소드는")
    class Describe_update {

        @Nested
        @DisplayName("상품에 수정할 내용이 있을 경우에")
        class Context_exist_update_product {

            Long productId;

            @BeforeEach
            void prepare() {

                Product form = createProduct();

                Product product = productService.register(form);
                productId = product.getId();

            }

            @Test
            @DisplayName("상품을 수정 후 수정한 상품을 리턴합니다.")
            void It_return_update_product() {

                ProductUpdateForm updateForm = ProductUpdateForm.builder()
                        .productName("updateName1")
                        .productPrice(2000)
                        .productDescription("updateDescription1")
                        .productImg("updateImg1")
                        .build();

                productService.update(productId, updateForm);
                Product result = productService.getProduct(productId);

                assertEquals(updateForm.getProductPrice(),result.getProductPrice());

            }

        }

        @AfterEach
        void cleanUp() {
            productRepository.findAll().clear();
        }

    }

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete {

        @Nested
        @DisplayName("삭제할 상품이 존재한다면")
        class Context_exist_deleteProduct{

            Long productId;

            @BeforeEach
            void prepare() {

                productService.getProducts().clear();

                Product form = createProduct();
                Product result = productService.register(form);
                productId = result.getId();

            }

            @Test
            @DisplayName("상품을 저장소에서 삭제합니다.")
            void It_delete_product() {

                productService.delete(productId);
                assertThat(productService.getProducts()).isEmpty();

            }

        }

    }

    private Product createProduct() {
        return Product.builder()
                .productName("name1")
                .productPrice(1000)
                .productDescription("description")
                .productImg("img1")
                .build();
    }

}