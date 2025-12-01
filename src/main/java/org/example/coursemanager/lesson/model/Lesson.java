package org.example.coursemanager.lesson.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.coursemanager.category.model.Category;
import org.example.coursemanager.course.model.Course;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "lesson_title", length = 120, nullable = false)
    private String title;

    @Column(name = "lesson_duration")
    private Integer duration;

    @Column(name = "lesson_difficulty_level", length = 20)
    private String difficultyLevel;

    @Column(name = "lesson_content", length = 1000)
    private String content;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
