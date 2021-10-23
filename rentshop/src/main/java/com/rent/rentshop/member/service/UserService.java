package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserRequest;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser();

    User join(User form);

}
