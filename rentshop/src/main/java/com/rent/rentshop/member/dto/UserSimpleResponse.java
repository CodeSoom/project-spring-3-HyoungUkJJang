package com.rent.rentshop.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleResponse {

    private String userId;
    private String userName;

    @Builder
    public UserSimpleResponse(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}
