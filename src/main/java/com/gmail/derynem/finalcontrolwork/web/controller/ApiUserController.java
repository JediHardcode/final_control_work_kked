package com.gmail.derynem.finalcontrolwork.web.controller;

import com.gmail.derynem.finalcontrolwork.service.UserService;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiUserController {
    private final UserService userService;

    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/root/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}