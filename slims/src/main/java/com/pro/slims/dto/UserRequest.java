package com.pro.slims.dto;

import com.pro.slims.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;
}