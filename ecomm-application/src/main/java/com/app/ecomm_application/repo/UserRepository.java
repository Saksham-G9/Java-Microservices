package com.app.ecomm_application.repo;

import org.springframework.stereotype.Repository;

import com.app.ecomm_application.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
