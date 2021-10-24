package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserService 클래스")
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Nested
    @DisplayName("join 메소드는")
    class Describe_join {

        @Nested
        @DisplayName("가입할 사용자가 있을 경우에")
        class Context_exist_join_user {

            User form;

            @BeforeEach
            void prepare() {
                form = createUser();
            }

            @Test
            @DisplayName("저장소에 사용자를 저장 후 리턴한다.")
            void It_save_return_user() {

                User result = userService.join(form);
                assertEquals(form.getUserName(), result.getUserName());

            }

        }

    }

    @Nested
    @DisplayName("userIdCheck 메소드는")
    class Describe_userIdCheck {

        @Nested
        @DisplayName("가입할 사용자의 아이디가 중복일 경우")
        class Context_userId_duplicate {

            String userId;

            @BeforeEach
            void prepare() {

                User form = createUser();
                User result = userService.join(form);
                userId = result.getUserId();

            }

            @Test
            @DisplayName("true를 리턴한다.")
            void It_return_test() {

                assertThat(userService.userIdCheck(userId)).isTrue();

            }
        }

        @Nested
        @DisplayName("가입할 사용자의 아이디가 중복일 아닐 경우")
        class Context_userId_notDuplicate {

            String userId;

            @BeforeEach
            void prepare() {

                User form = createUser();
                User result = userService.join(form);
                userId = result.getUserId() + "VALID";

            }

            @Test
            @DisplayName("true를 리턴한다.")
            void It_return_test() {

                assertThat(userService.userIdCheck(userId)).isFalse();

            }
        }
    }

    private User createUser() {
        return User.builder()
                .userId("id1")
                .password("1234")
                .userName("name1")
                .userEmail("mail1")
                .userPhone("010")
                .userBirth("1996")
                .userAddress(new Address("111-111", "address1"))
                .build();
    }

}
