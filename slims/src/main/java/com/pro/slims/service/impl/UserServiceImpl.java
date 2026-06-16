package com.pro.slims.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pro.slims.dto.UserRequest;
import com.pro.slims.dto.UserResponse;
import com.pro.slims.entity.User;
import com.pro.slims.exception.DuplicateResourceException;
import com.pro.slims.exception.ResourceNotFoundException;
import com.pro.slims.repository.UserRepository;
import com.pro.slims.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserResponse createUser(UserRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new DuplicateResourceException("Email already exists");
    }

    if (userRepository.existsByEmployeeCode(request.getEmployeeCode())) {
        throw new DuplicateResourceException("Employee Code already exists");
    }

        User user = User.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "User not found with id : " + id));

        return convertToResponse(user);
    }

    private UserResponse convertToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .employeeCode(user.getEmployeeCode())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setEmployeeCode(request.getEmployeeCode());

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        user.setRole(request.getRole());

        User updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }  

    @Transactional
    @Override
    public void deleteUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

}