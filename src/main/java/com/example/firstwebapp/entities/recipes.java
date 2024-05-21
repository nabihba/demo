package com.example.firstwebapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String instructions;
    private String description;
    private String ingredients;
    private String name;

    public recipes(Long id, String description, String ingredients, String name, String instructions) {
        this.instructions=instructions;
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
        this.name = name;
    }
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public recipes() {

    }
}
