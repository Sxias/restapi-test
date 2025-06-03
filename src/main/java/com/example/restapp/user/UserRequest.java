package com.example.restapp.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class DTO {
        private String name;

        public User toEntity(String username) {
            return User.builder().name(username).build();
        }
    }
}
