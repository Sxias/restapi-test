package com.example.restapp._core.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Resp {

    // 성공 응답
    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    // 실패 응답
    public static ResponseEntity<Map<String, String>> fail(HttpStatus status, String reason) {
        Map<String, String> body = new HashMap<>();
        body.put("reason", reason);
        return ResponseEntity.status(status).body(body);
    }
}