package com.spring.aiwebapp.repository;

import com.spring.aiwebapp.entity.TextPrompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextPromptRepository extends JpaRepository<TextPrompt, Long> {



}
