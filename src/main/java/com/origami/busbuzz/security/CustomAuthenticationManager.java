package com.origami.busbuzz.security;

import com.origami.busbuzz.entity.User;
import com.origami.busbuzz.exception.ResourceNotFoundException;
import com.origami.busbuzz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(isValidUser(username, password)){
            return new UsernamePasswordAuthenticationToken(username, password);
        }else {
            throw new AuthenticationException("invalid credentials") {
            };
        }

    }

    private boolean isValidUser(String username, String password){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserName", username));

        return passwordEncoder.matches(password, user.getPassword());
    }
}
