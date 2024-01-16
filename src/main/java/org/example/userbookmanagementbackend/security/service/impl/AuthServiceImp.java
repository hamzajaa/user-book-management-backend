package org.example.userbookmanagementbackend.security.service.impl;

import org.example.userbookmanagementbackend.security.bean.Role;
import org.example.userbookmanagementbackend.security.bean.User;
import org.example.userbookmanagementbackend.security.dao.RoleDao;
import org.example.userbookmanagementbackend.security.dao.UserDao;
import org.example.userbookmanagementbackend.security.dto.LoginDto;
import org.example.userbookmanagementbackend.security.dto.RegisterDto;
import org.example.userbookmanagementbackend.security.service.facade.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTTokenProvider jwtTokenProvider;


    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUserName(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setNumTel(registerDto.getNumTel());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleDao.findByName("ROLE_USER");
        userRole.ifPresent(roles::add);

        user.setRoles(roles);

        userDao.save(user);

        return "User register successfully";
    }


}
