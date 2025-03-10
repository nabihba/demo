package com.example.firstwebapp.services;

import com.example.firstwebapp.entities.Role;
import com.example.firstwebapp.entities.User;
import com.example.firstwebapp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class userServices {

    // ❌ Remove static keyword
    private final userRepository userRepository;

    // ✅ Use constructor injection
    @Autowired
    public userServices(userRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUser(User u) {
        return userRepository.save(u);
    }

    @Transactional
    public User addUser(User u) {
        System.out.println("Adding user: " + u.getEmail());
        if (userRepository.count() == 0) {
            u.setRole(Role.ROLE_ADMIN);
            System.out.println("First user detected, assigning ROLE_ADMIN");
        } else {
            u.setRole(Role.ROLE_USER);
            System.out.println("Assigning ROLE_USER");
        }

        User savedUser = userRepository.save(u);
        System.out.println("User saved with ID: " + savedUser.getId());

        return savedUser;
    }

    public ArrayList<User> getAll() {
        return (ArrayList<User>) userRepository.findAll();
    }

    // ❌ Remove static keyword
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> foundUser = userRepository.findByEmail(email);

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            if (user.getPassword().equals(password)) {  // Direct comparison (insecure but matches your case)
                return Optional.of(user);
            }
        }

        return Optional.empty(); // Return empty if password doesn't match
    }

}
