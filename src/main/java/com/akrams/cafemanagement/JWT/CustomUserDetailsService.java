package com.akrams.cafemanagement.JWT;

import com.akrams.cafemanagement.model.User;
import com.akrams.cafemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) return null;
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder().username(user.get().getUsername()).password(user.get().getPassword()).roles(user.get().getRole()).build();
    }

    public String userId(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(value -> value.getId().toString()).orElse(null);
    }

}
