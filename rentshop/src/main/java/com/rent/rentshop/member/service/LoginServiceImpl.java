package com.rent.rentshop.member.service;

import com.rent.rentshop.error.LoginFailException;
import com.rent.rentshop.error.UnauthorizedException;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.LoginData;
import com.rent.rentshop.member.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginData loginData) {
        User findUser = userService.getUser(loginData.getUserId());
        if(!findUser.passwordCheck(loginData.getPassword(),passwordEncoder)) {
            throw new LoginFailException();
        }
        return jwtUtil.createToken(findUser.getUserId());
    }

    @Override
    public String parseToken(String accessToken) {

        if(accessToken==null || accessToken.isBlank()) {
            throw new UnauthorizedException();
        }

        try {
            Claims decode = jwtUtil.parseToken(accessToken);
            String userId = decode.get("userId", String.class);
            return userId;
        }catch (SignatureException e) {
            throw new UnauthorizedException();
        }

    }

}
