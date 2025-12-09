package com.app.user.mapper;

import org.springframework.stereotype.Component;
import com.app.user.dto.AddressDto;
import com.app.user.dto.UserDto;
import com.app.user.entity.Address;
import com.app.user.entity.User;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserRole(user.getUserRole());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        
        if (user.getAddress() != null) {
            userDto.setAddress(addressToDto(user.getAddress()));
        }
        return userDto;
    }

    public AddressDto addressToDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }
}
