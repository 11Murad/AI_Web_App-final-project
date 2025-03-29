package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@AllArgsConstructor
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

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Response response;

    public Recipe() {
        this.createdDate = LocalDateTime.now();
    }

    public Recipe(String ingredients, String cuisine, String dietaryRestrictions, String language) {
        this.ingredients = ingredients;
        this.cuisine = cuisine;
        this.dietaryRestrictions = dietaryRestrictions;
        this.language = language;
        this.createdDate = LocalDateTime.now();
    }

}
