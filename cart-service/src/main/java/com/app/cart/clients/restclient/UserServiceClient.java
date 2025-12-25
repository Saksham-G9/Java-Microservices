package com.app.cart.clients.restclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.app.cart.dto.UserDto;

@HttpExchange
public interface UserServiceClient {

    @GetExchange("/api/users/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id);
}
