package com.triple.mileage.api.controller;

import com.triple.mileage.api.entity.User;
import com.triple.mileage.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping(path = "user/{id}")
    ResponseEntity<User> one(@PathVariable UUID userId) {
        User user = userRepository.findOne(userId);
        return ResponseEntity.ok(user);
    }



}
