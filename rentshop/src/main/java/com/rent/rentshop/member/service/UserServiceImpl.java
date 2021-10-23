package com.rent.rentshop.member.service;

import com.rent.rentshop.member.domain.User;
import com.rent.rentshop.member.dto.UserRequest;
import com.rent.rentshop.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User join(User form) {
        return userRepository.save(form);
    }

}
