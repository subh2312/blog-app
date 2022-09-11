package com.subhankar.blogappbackend.payloads;

import lombok.Data;

//Step 5
@Data
public class JwtAuthResponse {
    private String token;
}
