package com.app.ecomm_application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.app.ecomm_application.dto.AddressRequestDto;
import com.app.ecomm_application.dto.AddressResponseDto;
import com.app.ecomm_application.dto.UserRequestDto;
import com.app.ecomm_application.dto.UserResponseDto;
import com.app.ecomm_application.model.Address;
import com.app.ecomm_application.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserFromDto(UserRequestDto dto, @MappingTarget User user);

    UserResponseDto toDto(User user);

    Address toEntity(AddressRequestDto dto);

    AddressResponseDto toDto(Address address);
}