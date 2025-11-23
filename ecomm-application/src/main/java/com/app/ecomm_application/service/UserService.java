package com.app.ecomm_application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.ecomm_application.dto.UserRequestDto;
import com.app.ecomm_application.dto.UserResponseDto;
import com.app.ecomm_application.mapper.UserMapper;
import com.app.ecomm_application.model.User;
import com.app.ecomm_application.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDto> createUser(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        user.setId(null);
        User savedUser = userRepository.save(user);
        return Optional.of(userMapper.toDto(savedUser));
    }

    public Optional<UserResponseDto> getUser(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public void deleteUser(Long id) {
        if (id != null) {
            userRepository.deleteById(id);
        }
    }

    public Optional<UserResponseDto> updateUser(Long id, UserRequestDto dto) {
        if (id == null) {
            return Optional.empty();
        }
        User user = userMapper.toEntity(dto);
        user.setId(id);
        User savedUser = userRepository.save(user);
        return Optional.of(userMapper.toDto(savedUser));
    }
}
