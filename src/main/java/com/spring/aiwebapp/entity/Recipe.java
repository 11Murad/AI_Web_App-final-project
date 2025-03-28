package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ingredients;

    private String cuisine;

    @Column(name = "dietary_restrictions")
    private String dietaryRestrictions;

    private String language;

    @Column(nullable = false,columnDefinition = "TEXT" ,name = "recipe_text")
    private String recipeText;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;


    public Recipe() {
        this.createdDate = LocalDateTime.now();
    }

    public Recipe(String ingredients, String cuisine, String dietaryRestrictions, String language, String recipeText) {
        this.ingredients = ingredients;
        this.cuisine = cuisine;
        this.dietaryRestrictions = dietaryRestrictions;
        this.language = language;
        this.recipeText = recipeText;
        this.createdDate = LocalDateTime.now();
    }

}
