package org.example.coursemanager.content.service;

import org.example.coursemanager.content.model.Content;
import org.example.coursemanager.content.model.DateRange;
import org.example.coursemanager.content.repository.ContentRepository;
import org.example.coursemanager.lesson.model.Lesson;
import org.example.coursemanager.lesson.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final LessonRepository lessonRepository;
    public ContentService(ContentRepository contentRepository, LessonRepository lessonRepository) {
        this.contentRepository = contentRepository;
        this.lessonRepository = lessonRepository;
    }

    public Page<Content> getAllContents(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public void saveContent(Content content) {
        if (content.getLesson() != null && content.getLesson().getId() != null) {
            Lesson managedLesson = lessonRepository.findById(content.getLesson().getId())
                    .orElseThrow(() -> new RuntimeException("Lesson Not Found."));
            content.setLesson(managedLesson);
        }
        contentRepository.save(content);
    }

    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Content> filterContentsByCreatedAt(DateRange range, Pageable pageable) {
        if (range == null) {
            range = DateRange.ALL_TIME;
        }

        if (range == DateRange.ALL_TIME) {
            return contentRepository.findAll(pageable);
        }

        LocalDateTime startDate = LocalDateTime.now().minusMonths(range.getMonths());
        return contentRepository.findByCreatedAtAfter(startDate, pageable);
    }
}
