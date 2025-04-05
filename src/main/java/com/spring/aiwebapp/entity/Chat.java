package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "chat_history")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prompt> prompts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private User user;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public Chat() {
        this.createdDate = LocalDateTime.now();
    }

}
