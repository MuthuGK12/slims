package com.pro.slims;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pro.slims.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        // UserRequest request =
        //         UserRequest.builder()
        //                 .employeeCode("EMP002")
        //                 .firstName("John")
        //                 .lastName("Doe")
        //                 .email("john@slims.com")
        //                 .password("test123")
        //                 .role(Role.ANALYST)
        //                 .build();

        // UserResponse response =
        //         userService.createUser(request);

        // System.out.println(response);
    }
}