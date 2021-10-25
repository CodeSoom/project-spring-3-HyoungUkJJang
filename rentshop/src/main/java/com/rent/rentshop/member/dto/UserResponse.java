package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 상세조회
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userBirth;
    private String roadAddress;
    private String detailAddress;

    @Builder
    public UserResponse(String userId, String userName, String userEmail, String userPhone, String userBirth, String roadAddress, String detailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

}
