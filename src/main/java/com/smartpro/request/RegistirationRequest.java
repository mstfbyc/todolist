package com.smartpro.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistirationRequest {
    private String nameSurname;
    private String username;
    private String password;
    private String email;
}
