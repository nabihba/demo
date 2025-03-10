package com.example.firstwebapp.controles;

import com.example.firstwebapp.entities.recipes;
import com.example.firstwebapp.entities.User;
import com.example.firstwebapp.repository.recipeRepository;
import com.example.firstwebapp.repository.userRepository;
import com.example.firstwebapp.services.CloudinaryService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/recipes")
public class recipeController {

    private final recipeRepository recipeRepository;
    private final userRepository userRepository;
    private final CloudinaryService cloudinaryService;

    public recipeController(recipeRepository recipeRepository, userRepository userRepository, CloudinaryService cloudinaryService) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }
    @GetMapping("/user/{userId}")
    @ResponseBody
    public List<recipes> getUserRecipes(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId); // ✅ Fix: userRepository is now injected properly
        if (userOptional.isEmpty()) {
            return new ArrayList<>(); // Return empty list if user is not found
        }
        return recipeRepository.findByUser(userOptional.get()); // ✅ Fix here
    }

    // ✅ Fix 404 Error: Ensure this matches the frontend URL

    @GetMapping("/all")
    public ResponseEntity<List<recipes>> getAllRecipes() {
        try {
            // Fetching all recipes
            List<recipes> recipeList = recipeRepository.findAll();

            // If no recipes found, return a 204 No Content status
            if (recipeList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // If recipes are found, return them with a 200 OK status
            return ResponseEntity.ok(recipeList);

        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();

            // If there's an exception, return a 500 Internal Server Error with a custom error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Map<String, String>> addRecipe(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("category") String category,
            @RequestParam("youtubeUrl") String youtubeUrl,
            @RequestParam("image") MultipartFile image) {

        Map<String, String> response = new HashMap<>();

        try {
            // Upload image to Cloudinary
            String imageUrl = cloudinaryService.uploadImage(image);

            // Create a new recipe instance
            recipes recipe = new recipes();
            recipe.setName(name);
            recipe.setDescription(description);
            recipe.setIngredients(ingredients);
            recipe.setInstructions(instructions);
            recipe.setCategory(category);
            recipe.setYoutubeUrl(youtubeUrl);
            recipe.setImageUrl(imageUrl);

            // Save the recipe
            recipeRepository.save(recipe);

            response.put("message", "Recipe added successfully!");
            response.put("recipeId", String.valueOf(recipe.getId())); // Send the ID of the new recipe
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "Image upload failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

