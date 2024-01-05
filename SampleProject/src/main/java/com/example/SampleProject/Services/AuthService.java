package com.example.SampleProject.Services;


import com.example.SampleProject.dtos.SignupDTO;
import com.example.SampleProject.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
