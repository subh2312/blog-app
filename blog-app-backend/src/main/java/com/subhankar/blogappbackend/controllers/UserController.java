package com.subhankar.blogappbackend.controllers;

import com.subhankar.blogappbackend.dto.UserDto;
import com.subhankar.blogappbackend.payloads.ApiResponse;
import com.subhankar.blogappbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.updateUser(userDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return new  ResponseEntity(new ApiResponse("User deleted Successfully",true,HttpStatus.OK.value()),HttpStatus.OK);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
}
