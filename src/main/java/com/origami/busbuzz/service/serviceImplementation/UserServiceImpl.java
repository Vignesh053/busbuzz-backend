package com.origami.busbuzz.service.serviceImplementation;

import com.origami.busbuzz.dto.JwtAuthResponse;
import com.origami.busbuzz.dto.LoginDto;
import com.origami.busbuzz.dto.UserDto;
import com.origami.busbuzz.entity.User;
import com.origami.busbuzz.exception.EmailAlreadyExistsException;
import com.origami.busbuzz.exception.ResourceNotFoundException;
import com.origami.busbuzz.repository.UserRepository;
import com.origami.busbuzz.security.JwtTokenFilter;
import com.origami.busbuzz.security.JwtTokenUtil;
import com.origami.busbuzz.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private PasswordEncoder passwordEncoder;


    @Override
    public String createUser(UserDto userDto) {


        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailAlreadyExistsException(userDto.getEmail());
        } else if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Username already exists";
        }

        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setPhonenumber(userDto.getPhonenumber());

        userRepository.save(newUser);
        return "registration successful";
    }

    @Override
    public JwtAuthResponse loginUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken uptoken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication auth = authenticationManager.authenticate(uptoken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenUtil.generateToken(loginDto.getUsername());

        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserName", loginDto.getUsername()));

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAuthenticated(auth.isAuthenticated());
        response.setUserId(user.getId());
        response.setAccessToken(jwtToken);

        return response;
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
