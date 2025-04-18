package com.spring.aiwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "text_prompt")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TextPrompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_chat_id", nullable = false)
    private TextChat textChat;

    @OneToOne(mappedBy = "prompt" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private TextResponse textResponse;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;


}
