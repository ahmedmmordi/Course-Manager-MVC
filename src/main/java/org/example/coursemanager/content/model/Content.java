package org.example.coursemanager.content.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.coursemanager.lesson.model.Lesson;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_contents")
@Getter
@Setter
@NoArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "content_id")
    private Long id;

    @Column(name = "content_video_url", length = 150, nullable = false)
    private String videoUrl;

    @Column(name = "content_document_url", length = 150)
    private String documentUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "content_created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "content_updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
