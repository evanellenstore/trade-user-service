package com.trade.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.trade.user.dto.*;
import com.trade.user.entity.User;
import com.trade.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse create(UserRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) 
                .role(request.getRole())
                .active(request.getActive())
                .createdAt(LocalDateTime.now())
                .build();

        return map(repository.save(user));
    }

    public List<UserResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        return map(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse update(Long id, UserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(request.getActive());
        return map(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    public UserResponse getByUsername(String username) {
        return map(repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    private UserResponse map(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .role(u.getRole())
                .password(u.getPassword())
                .active(u.getActive())
                .createdAt(u.getCreatedAt())
                .build();
    }
}
