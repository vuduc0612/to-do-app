package com.example.todoapp.security;

import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName().toString()))
//                .collect(Collectors.toSet());
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName().toString()))
//                .collect(Collectors.toSet());
//        return new org.springframework.security.core.userdetails.User(
//
//                user.getId().toString(),
//                user.getPassword(),
//                authorities
//        );

        //Using UserPrincipal
        return UserPrincipal.create(user);
    }
}
