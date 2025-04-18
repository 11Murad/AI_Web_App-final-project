package com.spring.aiwebapp.repository;
import com.spring.aiwebapp.entity.TextResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextResponseRepository extends JpaRepository<TextResponse, Long> {
}
