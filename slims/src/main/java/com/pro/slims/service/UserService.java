package com.pro.slims.service;

import java.util.List;

import com.pro.slims.dto.UserRequest;
import com.pro.slims.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}