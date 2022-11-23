package com.Together.Community.Service;

import com.Together.Community.Domain.Role;
import com.Together.Community.Domain.User;
import com.Together.Community.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setId(user.getId());
        user.setPassword(encodedPassword);
        user.setName(user.getName());
        user.setNickname(user.getNickname());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setProfile(user.getProfile());
        user.setTemperature(36.5);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
