package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.UserDto;
import com.subhankar.blogappbackend.entities.Role;
import com.subhankar.blogappbackend.entities.User;
import com.subhankar.blogappbackend.exceptions.ResourceNotFoundException;
import com.subhankar.blogappbackend.repositories.Rolerepository;
import com.subhankar.blogappbackend.repositories.UserRepository;
import com.subhankar.blogappbackend.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Rolerepository rolerepository;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role =rolerepository.findById(AppConstants.USER_ROLE).orElseThrow();
        user.getRoles().add(role);

        User newUser = userRepository.save(user);
        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        return modelMapper.map(userRepository.save(modelMapper.map(userDto,User.class)),UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id)),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId)));

    }
}
