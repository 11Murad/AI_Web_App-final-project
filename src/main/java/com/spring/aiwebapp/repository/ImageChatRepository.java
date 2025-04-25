package com.spring.aiwebapp.repository;
import com.spring.aiwebapp.entity.ImageChat;
import com.spring.aiwebapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ImageChatRepository extends JpaRepository<ImageChat, Long> {
    Optional <ImageChat> findByIdAndUser(Long id, User user);

    Page<ImageChat> findByUser(User user, Pageable pageable);

    void deleteByIdAndUser(Long id, User user);

}
