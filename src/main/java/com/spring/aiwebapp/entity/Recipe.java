package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @OneToOne(mappedBy = "recipe",orphanRemoval = true)
    private Response response;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdDate;

}
