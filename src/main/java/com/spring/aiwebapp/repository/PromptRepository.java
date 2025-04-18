package com.spring.aiwebapp.repository;

import com.spring.aiwebapp.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {



}
