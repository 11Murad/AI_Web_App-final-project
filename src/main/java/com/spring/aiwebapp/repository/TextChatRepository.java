package com.spring.aiwebapp.repository;
import com.spring.aiwebapp.entity.TextChat;
import com.spring.aiwebapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TextChatRepository extends JpaRepository<TextChat, Long> {

    List<TextChat> findByUserOrderByCreatedAtDesc(User user);

    Optional<TextChat> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);

    Page<TextChat> findByUser(User user, Pageable pageable);

}
