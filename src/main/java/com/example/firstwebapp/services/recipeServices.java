package com.example.firstwebapp.services;

import com.example.firstwebapp.entities.recipes;
import com.example.firstwebapp.repository.recipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class recipeServices {
    private final userServices userServices;

    @Autowired
    public recipeServices(userServices userServices) {
        this.userServices = userServices;
    }

    @Autowired
    private recipeRepository repository;

    public recipes addRecipe(recipes u) {
        return repository.save(u);
    }

//    public boolean toggleFavorite(Long recipeId, Long userId) {
//        System.out.println(recipeId+","+"we");
//        Optional<recipes> recipeOpt = repository.findById(recipeId);
//        Optional<user> userOpt = userServices.findUserById(userId);
//
//        if (recipeOpt.isPresent() && userOpt.isPresent()) {
//            recipes recipe = recipeOpt.get();
//            user user = userOpt.get();
//
//            if (recipe.getFavoritedByUsers().contains(user)) {
//                recipe.getFavoritedByUsers().remove(user);
//                repository.save(recipe);
//                return false;
//            } else {
//                recipe.getFavoritedByUsers().add(user);
//                repository.save(recipe);
//                return true;
//            }
//        }
//        return false;
//    }

    public List<recipes> getAll() {
        return repository.findAll();
    }

    public Optional<recipes> findRecipesById(Long id) {
        return repository.findById(id);
    }

    public List<recipes> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public void deleteRecipe(Long id) {
        repository.deleteById(id);
    }
}
