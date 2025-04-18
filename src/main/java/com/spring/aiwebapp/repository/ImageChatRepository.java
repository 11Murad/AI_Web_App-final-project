package com.spring.aiwebapp.repository;

import com.spring.aiwebapp.entity.ImageChat;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ImageChatRepository extends JpaRepository<ImageChat, Long> {

    Optional <ImageChat> findByIdAndUser(Long id, User user);

    List<ImageChat> findByUserOrderByCreatedAtDesc(User user);

    Page<ImageChat> findByUser(User user, PageRequest pageRequest);

}
