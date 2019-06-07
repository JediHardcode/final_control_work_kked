package com.gmail.derynem.finalcontrolwork.service;

import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getByName(String name);

    List<UserDTO> getAllUsers();
}