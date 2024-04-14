package com.ocprojettrois.locationprojettree.Models.User.UserDto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
