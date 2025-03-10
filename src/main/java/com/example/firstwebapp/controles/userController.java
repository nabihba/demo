package com.example.firstwebapp.controles;

import com.example.firstwebapp.repository.userRepository;
import com.example.firstwebapp.services.userServices;
import com.example.firstwebapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api") // Base URL for all user-related endpoints
public class userController {

    @Autowired
    private userServices userServices;

    @Autowired
    private userRepository userRepository;


    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        System.out.println("Register endpoint hit!");

        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");
        }

        User savedUser = userServices.addUser(newUser);
        if (savedUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not saved!");
        }

        return ResponseEntity.ok("User registered successfully!");
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userServices.findUserById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());  // Correct return type
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");  // String message as ResponseEntity<?>
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServices.getAll());
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userServices.findUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Make sure the role is included in the response.
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        System.out.println("Login attempt for email: " + loginUser.getEmail());

        Optional<User> foundUser = userRepository.login(loginUser.getEmail(), loginUser.getPassword());

        if (foundUser.isEmpty()) {
            System.out.println("Login failed: Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
        }

        System.out.println("Login successful: " + foundUser.get().getEmail());
        return ResponseEntity.ok(foundUser.get());
    }








    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userServices.findUserById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

            userServices.addUser(existingUser);
            return ResponseEntity.ok("Profile updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }
}
