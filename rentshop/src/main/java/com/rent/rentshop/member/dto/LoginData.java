package com.rent.rentshop.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginData {

    @NotEmpty(message = "아이디는 필수 입력 항목입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @Builder
    public LoginData(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    LoginData() {}

}
