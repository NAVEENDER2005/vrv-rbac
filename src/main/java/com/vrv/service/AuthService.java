package com.vrv.service;



import com.vrv.dto.LoginRequest;
import com.vrv.dto.RegisterRequest;
import com.vrv.entity.Role;
import com.vrv.entity.User;

import com.vrv.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service

public class AuthService {
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository;
    private final Argon2PasswordEncoder passwordEncoder;
    
    

    public AuthService(UserRepository userRepository, RoleRepository roleRepository,
			Argon2PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(new HashSet<>());
        user.getRoles().add(role);

        userRepository.save(user);
    }

    public boolean login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
