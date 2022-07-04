package com.triple.mileage.api.dto;

import com.triple.mileage.api.domain.User;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

public class UserDto {

    @Data
    public static class UserRequest {
        private UUID userId;

        public User toEntity() {
            return User.builder()
                    .id(userId)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private UUID userId;

        public Res(User user) {
            this.userId = user.getId();
        }
    }
}
