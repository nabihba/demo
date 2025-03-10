package com.example.firstwebapp.repository;

import com.example.firstwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

    // Find a user by their ID (Spring Data JPA provides this automatically)
    Optional<User> findById(Long id);


    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> login(@Param("email") String email, @Param("password") String password);



    Optional<User> findByEmail(String email);
}
