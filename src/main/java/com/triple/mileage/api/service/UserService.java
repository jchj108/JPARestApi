package com.triple.mileage.api.service;

import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user;
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findOne(user.getId());
        if (findUser != null) {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(UUID id) {
        User findUser = userRepository.findOne(id);
        if (findUser == null) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다");
        }
        return findUser;
    }
}
