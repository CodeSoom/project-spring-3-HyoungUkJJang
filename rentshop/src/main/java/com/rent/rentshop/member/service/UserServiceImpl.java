package com.rent.rentshop.member.service;

import com.rent.rentshop.error.UserNotFoundException;
import com.rent.rentshop.member.domain.User;
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

    @Override
    public boolean userIdCheck(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean userEmailCheck(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    public void userDelete(Long id) {

        User result = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        userRepository.delete(result);

    }
}
