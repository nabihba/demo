package com.example.firstwebapp.controles;

import com.example.firstwebapp.entities.recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
@RestController
public class recipeController {

    @Autowired
    private com.example.firstwebapp.services.recipeServices recipeServices;

    @PostMapping("/users/addRecipe")
    public @ResponseBody recipes addRecipe(@RequestBody recipes u) {
            return recipeServices.addRecipe(u);
        }

        @GetMapping("/users/getAll")
        public @ResponseBody ArrayList<recipes> getAll() {
            return recipeServices.getAll();
        }

        @GetMapping("/user/findRecipesById/{id}")
        public @ResponseBody Optional<recipes> findRecipesById(@PathVariable Long id) {
            return recipeServices.findRecipesById(id);
        }
        @GetMapping("/recipes/user/{userId}")
         public @ResponseBody Optional<recipes> getRecipesByUserId(@PathVariable Long userId) {
          return recipeServices.findRecipesById(userId);
    }

    }
