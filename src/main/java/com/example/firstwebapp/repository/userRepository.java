package com.example.firstwebapp.repository;

import com.example.firstwebapp.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
    @Query("select a from user a where a.email= :email and a.password= :password")
    public user  login(String email, String password);

}