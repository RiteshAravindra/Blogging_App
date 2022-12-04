package com.coderitesh.bloogingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderitesh.bloogingapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
