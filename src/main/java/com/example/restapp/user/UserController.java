package com.example.restapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id) {

        return null;
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable long id) {

        return null;
    }

    @PostMapping("/users")
    public String joinUser() {

        return null;
    }
}
