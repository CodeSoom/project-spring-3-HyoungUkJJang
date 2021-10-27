package com.rent.rentshop.controller;

import com.rent.rentshop.member.domain.Address;
import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserRequest;
import com.rent.rentshop.member.dto.UserResponse;
import com.rent.rentshop.member.dto.UserSimpleResponse;
import com.rent.rentshop.member.dto.UserUpdate;
import com.rent.rentshop.member.service.UserService;
import com.rent.rentshop.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseData getUsers() {
        List<UserSimpleResponse> result = userService.getUsers()
                .stream()
                .map(u -> new UserSimpleResponse(
                        u.getUserId(),
                        u.getUserName()))
                .collect(Collectors.toList());

        return new ResponseData(result);
    }

    @GetMapping("/{userId}")
    public ResponseData getUser(@PathVariable("userId") String userId) {

        User findUser = userService.getUser(userId);

        UserResponse result = UserResponse.builder()
                .userId(findUser.getUserId())
                .userName(findUser.getUserName())
                .userEmail(findUser.getUserEmail())
                .userPhone(findUser.getUserPhone())
                .userBirth(findUser.getUserBirth())
                .roadAddress(findUser.getUserAddress().getRoadAddress())
                .detailAddress(findUser.getUserAddress().getDetailAddress())
                .build();

        return new ResponseData(result);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData register(@RequestBody @Valid UserRequest form) {

        User userForm = User.builder()
                .userId(form.getUserId())
                .password(form.getPassword())
                .userName(form.getUserName())
                .userEmail(form.getUserEmail())
                .userPhone(form.getUserPhone())
                .userBirth(form.getUserBirth())
                .userAddress(new Address(form.getRoadAddress(), form.getDetailAddress()))
                .build();

        User joinUser = userService.join(userForm);

        UserResponse result = UserResponse.builder()
                .userId(joinUser.getUserId())
                .userName(joinUser.getUserName())
                .userEmail(joinUser.getUserEmail())
                .userPhone(joinUser.getUserPhone())
                .userBirth(joinUser.getUserBirth())
                .roadAddress(joinUser.getUserAddress().getRoadAddress())
                .detailAddress(joinUser.getUserAddress().getDetailAddress())
                .build();

        return new ResponseData(result);

    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value = "/{userId}")
    public void updateUser(@PathVariable("userId") String userId,
                           @RequestBody @Valid UserUpdate form) {

        userService.userUpdate(userId, form);

    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        userService.userDelete(userId);
    }

}
