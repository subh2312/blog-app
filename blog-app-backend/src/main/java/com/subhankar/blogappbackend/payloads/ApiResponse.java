package com.subhankar.blogappbackend.payloads;

import com.subhankar.blogappbackend.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;
    private Integer statusCode;

    public ApiResponse(String message, boolean status, Integer statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }
}
