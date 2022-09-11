package com.subhankar.blogappbackend.dto;

import com.subhankar.blogappbackend.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotNull
    @Size(min = 4, max = 50, message = "Size Must be between 4 and 50")
    private String name;
    @NotNull
    @Email(message = "Invalid Email")
    private String email;
    @NotNull
    @Size(min = 8, max = 8,message = "Size Must be 8")
    private String password;
    @NotNull
    @Size(min = 5, max = 200,message = "Size Must be between 5 and 200")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
