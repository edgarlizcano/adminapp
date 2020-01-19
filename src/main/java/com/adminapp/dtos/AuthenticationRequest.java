package com.adminapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String ip;
}
