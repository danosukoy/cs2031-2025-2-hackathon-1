package com.oreo.controller;

import com.oreo.domain.User;
import com.oreo.dto.UserRequestDto;
import com.oreo.dto.UserResponseDto;
import com.oreo.dto.UserMapper;
import com.oreo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto request) {
        User user = UserMapper.toEntity(request);
        User saved = userService.register(user);
        return ResponseEntity.ok(UserMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
}