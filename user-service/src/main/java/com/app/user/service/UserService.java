package com.app.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.user.dto.UserDto;
import com.app.user.dto.UserRequestDto;
import com.app.user.entity.Address;
import com.app.user.entity.User;
import com.app.user.mapper.UserMapper;
import com.app.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto createUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setUserRole(userRequestDto.getUserRole());
        
        if (userRequestDto.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequestDto.getAddress().getStreet());
            address.setCity(userRequestDto.getAddress().getCity());
            address.setState(userRequestDto.getAddress().getState());
            address.setPostalCode(userRequestDto.getAddress().getPostalCode());
            address.setCountry(userRequestDto.getAddress().getCountry());
            user.setAddress(address);
        }
        
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto).orElse(null);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userRequestDto.getFirstName());
            user.setLastName(userRequestDto.getLastName());
            user.setEmail(userRequestDto.getEmail());
            user.setPhone(userRequestDto.getPhone());
            user.setUserRole(userRequestDto.getUserRole());
            
            User updatedUser = userRepository.save(user);
            return userMapper.toDto(updatedUser);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
