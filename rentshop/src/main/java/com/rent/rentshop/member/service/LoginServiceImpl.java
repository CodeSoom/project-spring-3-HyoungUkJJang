package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.LoginData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginData loginData) {
        User findUser = userService.getUser(loginData.getUserId());
        if(findUser.passwordCheck(loginData.getPassword(),passwordEncoder)) {
            return "Login Success";
        }
        return "Login Fail";
    }

}
