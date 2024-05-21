package com.example.firstwebapp.controles;
import com.example.firstwebapp.services.userServices;
import com.example.firstwebapp.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class userController {

    @Autowired
    private userServices userServices;

    @PostMapping("/users/addUser")
    public @ResponseBody user addUser(@RequestBody user u) {
        return userServices.addUser(u);
    }

    @GetMapping("/users/getAllusers")
    public @ResponseBody ArrayList<user> getAll() {
        return userServices.getAll();
    }

    @GetMapping("/user/findUserId/{id}")
    public @ResponseBody Optional<user> findUserById(@PathVariable Long id) {
        return userServices.findUserById(id);
    }

    @GetMapping("/user/login/{email}/{password}")
    public @ResponseBody user login(@PathVariable String email, @PathVariable String password) {
        return userServices.login(email, password);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable Long id, @RequestBody user updatedUser) {
        try {
            Optional<user> userOptional = userServices.findUserById(id);
            if (userOptional.isPresent()) {
                user existingUser = userOptional.get();
                // Update profile data
                existingUser.setFullName(updatedUser.getFullName());
                existingUser.setuserName(updatedUser.getuserName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setAddress(updatedUser.getAddress());
                existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                //existingUser.setPassword(updatedUser.getPassword());
                // Save updated user
                userServices.addUser(existingUser);
                return ResponseEntity.ok("Profile updated successfully");
            } else {
                return ResponseEntity.notFound().build(); // User not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile");
        }
    }
}


