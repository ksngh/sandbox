package com.practice.paymentassignment.model.dto.user;

import com.practice.paymentassignment.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserCreate {

    @NoArgsConstructor
    @Getter
    public static class Request {
        private String name;

        public User toEntity(Request request) {
            return User.of(request.name);
        }
    }

    @AllArgsConstructor
    public static class Response {
        private Long id;

        public static Response fromEntity(User user) {
            return new Response(user.getId());
        }

    }
}
