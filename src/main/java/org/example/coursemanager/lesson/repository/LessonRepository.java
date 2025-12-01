package org.example.coursemanager.lesson.repository;

import org.example.coursemanager.lesson.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Page<Lesson> findByDifficultyLevelIgnoreCase(String difficultyLevel, Pageable pageable);
    Page<Lesson> findByDurationBetween(Integer minDuration, Integer maxDuration, Pageable pageable);
}
