package com.triple.mileage.api.controller;

import com.triple.mileage.api.common.ErrorResponse;
import com.triple.mileage.api.domain.User;
import com.triple.mileage.api.repository.UserRepository;
import com.triple.mileage.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @GetMapping(path = "users/{userId}")
    public User one(@PathVariable("userId") UUID userId) {
        return userService.findOne(userId);
    }

    @PostMapping(path = "users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User saveMember(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }
}
