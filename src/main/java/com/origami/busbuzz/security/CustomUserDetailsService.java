package com.origami.busbuzz.security;

import com.origami.busbuzz.entity.User;
import com.origami.busbuzz.exception.ResourceNotFoundException;
import com.origami.busbuzz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserName", username));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Collections.emptyList());
    }
}
