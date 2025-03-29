package com.spring.aiwebapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prompt")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @OneToOne( mappedBy = "prompt",cascade = CascadeType.ALL)
    private Response response;

    @ManyToOne(cascade = CascadeType.ALL)
    private Chat chat;
}
