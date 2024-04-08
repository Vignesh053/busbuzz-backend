package com.origami.busbuzz.service.serviceImplementation;

import com.origami.busbuzz.dto.UserDto;
import com.origami.busbuzz.entity.User;
import com.origami.busbuzz.repository.UserRepository;
import com.origami.busbuzz.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public String createUser(UserDto userDto) {


        if(userRepository.existsByEmail(userDto.getEmail())){
            return "Email already Exists";
        } else if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Username already exists";
        }

        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhonenumber(userDto.getPhonenumber());

        userRepository.save(newUser);
        return "registration successful";
    }

    @Override
    public UserDto getUser(Long id) {

        User user = userRepository.findById(id).get();



        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id , UserDto userDto) {
        User user = userRepository.findById(id).get();
        user.setPhonenumber(userDto.getPhonenumber());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        if(userDto.getPassword() != null && userDto.getPassword() != user.getPassword()){
            user.setPassword(userDto.getPassword());
        }
        UserDto updatedUserDto = modelMapper.map(userRepository.save(user), UserDto.class);

        return updatedUserDto;
    }

    @Override
    public Optional<User> getUser() {
        return Optional.empty();
    }
}
