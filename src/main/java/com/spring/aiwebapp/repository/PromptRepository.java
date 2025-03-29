package com.spring.aiwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptRepository extends JpaRepository<PromptRepository, Long> {
}
