package com.spring.aiwebapp.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String response;
}
