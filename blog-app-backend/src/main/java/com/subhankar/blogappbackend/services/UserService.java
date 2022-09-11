package com.subhankar.blogappbackend.services;


import com.subhankar.blogappbackend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
