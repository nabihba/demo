package com.example.firstwebapp.services;
import com.example.firstwebapp.entities.recipes;
import com.example.firstwebapp.repository.recipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class recipeServices {
    @Autowired
    private recipeRepository repository;

    public recipes addRecipe(recipes u)
    {
        return repository.save(u);
    }
    public ArrayList<recipes> getAll()
    {
        return (ArrayList<recipes>) repository.findAll();
    }
    public Optional<recipes> findRecipesById(Long id)
    {
        return repository.findById(id);
    }
}
