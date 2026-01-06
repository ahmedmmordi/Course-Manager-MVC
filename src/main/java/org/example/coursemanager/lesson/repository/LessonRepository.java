package org.example.coursemanager.lesson.repository;

import org.example.coursemanager.lesson.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("""
        SELECT l FROM Lesson l
            WHERE (:difficulty IS NULL OR l.difficultyLevel = :difficulty)
            AND l.duration >= 0
            AND l.duration <= COALESCE(:max, 2147483647)
            AND (:published IS NULL OR l.isPublished = :published)
    """)
    Page<Lesson> filterLessons(@Param("difficulty") String difficulty, @Param("max") Integer max, @Param("published") Boolean published, Pageable pageable);
}
