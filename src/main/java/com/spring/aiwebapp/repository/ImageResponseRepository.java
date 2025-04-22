package com.spring.aiwebapp.repository;
import com.spring.aiwebapp.entity.PictureResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageResponseRepository extends JpaRepository<PictureResponse, Long> {

}
