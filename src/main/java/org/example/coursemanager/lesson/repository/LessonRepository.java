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
    @Query(value = """
        SELECT * FROM lessons
        WHERE (:difficulty IS NULL OR lesson_difficulty_level = :difficulty)
        AND ((:min IS NULL OR :max IS NULL) OR lesson_duration BETWEEN :min AND :max)""",
            countQuery = """
        SELECT COUNT(*) FROM lessons
        WHERE (:difficulty IS NULL OR lesson_difficulty_level = :difficulty)
        AND ((:min IS NULL OR :max IS NULL) OR lesson_duration BETWEEN :min AND :max)""",
            nativeQuery = true
    )
    Page<Lesson> filterLessons(@Param("difficulty") String difficulty, @Param("min") Integer min, @Param("max") Integer max, Pageable pageable);
}
