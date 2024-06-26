package com.origami.busbuzz.service;

import com.origami.busbuzz.dto.JwtAuthResponse;
import com.origami.busbuzz.dto.LoginDto;
import com.origami.busbuzz.dto.UserDto;
import com.origami.busbuzz.entity.User;

import java.util.Optional;

public interface UserService {

    public  String createUser(UserDto userDto);

    JwtAuthResponse loginUser(LoginDto loginDto);

    public UserDto getUser(Long id);

    public UserDto updateUser(Long id,UserDto userDto);


    Optional<User> getUser();
}
