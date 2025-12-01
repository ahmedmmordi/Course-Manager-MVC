package org.example.coursemanager.lesson.service;

import org.example.coursemanager.lesson.model.Lesson;
import org.example.coursemanager.lesson.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public boolean deleteLesson(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Lesson> filterLessonsByDifficulty(String difficulty, Pageable pageable) {
        return lessonRepository.findByDifficultyLevelIgnoreCase(difficulty, pageable);
    }

    public Page<Lesson> filterLessonsByDuration(Integer minDuration, Integer maxDuration, Pageable pageable) {
        if (minDuration == null) minDuration = 0;
        if (maxDuration == null) maxDuration = Integer.MAX_VALUE;
        return lessonRepository.findByDurationBetween(minDuration, maxDuration, pageable);
    }
}
