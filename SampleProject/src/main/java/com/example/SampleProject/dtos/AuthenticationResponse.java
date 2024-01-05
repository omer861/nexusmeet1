package com.example.SampleProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private Map jwt;


}
