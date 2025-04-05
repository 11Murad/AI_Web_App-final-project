package com.spring.aiwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "prompt")
@AllArgsConstructor
@NoArgsConstructor
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @OneToOne(cascade = CascadeType.ALL)
    private Response response;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public Prompt(String prompt) {
        this.createdDate = LocalDateTime.now();
    }

}
