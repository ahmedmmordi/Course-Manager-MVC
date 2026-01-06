package org.example.coursemanager.content.repository;

import org.example.coursemanager.content.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    @EntityGraph(attributePaths = "lesson")
    Page<Content> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "lesson")
    Page<Content> findAll(Pageable pageable);
}
