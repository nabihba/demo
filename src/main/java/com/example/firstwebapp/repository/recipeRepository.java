package com.example.firstwebapp.repository;
import com.example.firstwebapp.entities.recipes;
import com.example.firstwebapp.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;
@Repository
    public interface recipeRepository extends JpaRepository<recipes,Long>
{}