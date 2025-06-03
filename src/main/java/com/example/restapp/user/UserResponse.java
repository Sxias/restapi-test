package com.example.restapp.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class DTO {
        private Long id;
        private String name;

        public DTO(User user) {
            this.name = user.getName();
            this.id = user.getId();
        }
    }

    @Data
    public static class SaveDTO {
        private String name;

        public SaveDTO(User user) {
            this.name = user.getName();
        }
    }
}
