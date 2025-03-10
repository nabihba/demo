package com.example.firstwebapp.repository;

import com.example.firstwebapp.entities.recipes;
import com.example.firstwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface recipeRepository extends JpaRepository<recipes, Long> {

    
    Optional<recipes> findById(Long id);
    List<recipes> findByCategory(String category);

    List<recipes> findByUser(User user);
}

