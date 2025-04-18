package com.spring.aiwebapp.repository;

import com.spring.aiwebapp.entity.Chat;
import com.spring.aiwebapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByUserOrderByCreatedAtDesc(User user);

    Optional<Chat> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);

    Page<Chat> findByUser(User user, PageRequest pageRequest);

    Optional<Chat> findChatByPromptId(Long promptId);
}
