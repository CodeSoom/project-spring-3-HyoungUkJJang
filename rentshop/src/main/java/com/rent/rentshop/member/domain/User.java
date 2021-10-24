package com.rent.rentshop.member.domain;

import com.rent.rentshop.product.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 회원의 메인 도메인
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBirth;

    @Embedded
    private Address userAddress;

    @Builder
    public User(Long id, String userId, String password, String userName, String userEmail, String userPhone, String userBirth, Address userAddress) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userAddress = userAddress;
    }

    public void updateUser(String password, String userEmail, String userPhone, String roadAddress, String detailAddress) {

        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = new Address(roadAddress, detailAddress);

    }

}
