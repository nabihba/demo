package com.example.firstwebapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ✅ ID field should be a single unique identifier

    private String name;
    private String description;
    private String ingredients;
    private String instructions;
    private String youtubeUrl;
    private String imageUrl;

    @Column(name = "category")
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")  // This will link the 'recipes' entity to 'user' entity
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public recipes() {}

    public recipes(Long id, String name, String description, String ingredients, String instructions, String category, String youtubeUrl, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
        this.youtubeUrl = youtubeUrl;
        this.imageUrl = imageUrl;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        recipes recipe = (recipes) obj;
        return id != null && id.equals(recipe.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // ✅ Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }




    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getYoutubeUrl() { return youtubeUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

}
