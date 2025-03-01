package com.example.todoapp.service.iml;

import com.example.todoapp.dto.AuthDto;
import com.example.todoapp.dto.LoginDto;
import com.example.todoapp.entity.ERoleType;
import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.RoleRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.security.JwtTokenProvider;
import com.example.todoapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String signup(AuthDto authDto) {
        String email = authDto.getEmail();
        if(userRepository.existsByEmail(email)){
            return "Email already exists";
        }

        String password = authDto.getPassword();
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setUsername(authDto.getUsername());
        Role userRole = roleRepository.findByName(ERoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        System.out.println(userRole);
        newUser.setRoles(Set.of(userRole));
        userRepository.save(newUser);

        return "User registered successfully";
    }

    @Override
    public String signin(LoginDto loginDto) {
        String email = loginDto.getEmail();
        if(!userRepository.existsByEmail(email)){
            return "Email not exists";
        }
        User user = userRepository.findByEmail(email).get();
        System.out.println(user.getPassword());
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            return "Password is incorrect";
        }
        Long userId = user.getId();
        System.out.println("User id: " + userId);

        return jwtTokenProvider.generateToken(email, userId);
    }

}
