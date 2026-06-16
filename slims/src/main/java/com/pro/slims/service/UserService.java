package com.pro.slims.service;

import com.pro.slims.dto.UserRequest;
import com.pro.slims.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);
}