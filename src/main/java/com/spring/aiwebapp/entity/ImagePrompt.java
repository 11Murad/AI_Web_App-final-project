package com.spring.aiwebapp.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "image_prompts")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ImagePrompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String prompt;

    private String quality;
    private int n ;
    private int height;
    private int width;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_chat_id", nullable = false)
    private ImageChat imageChat;

    @OneToMany(mappedBy = "imagePrompt", cascade = CascadeType.ALL)
    private List<PictureResponse> responses;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
}

