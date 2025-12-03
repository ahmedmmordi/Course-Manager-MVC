package org.example.coursemanager.lesson.controller;

import org.example.coursemanager.lesson.model.Lesson;
import org.example.coursemanager.lesson.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public String listLessons(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.getAllLessons(pageable);
        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("activeFilters", Map.of("difficulty", "", "min", "", "max", ""));
        return "lesson/list";
    }

    @GetMapping("/create")
    public String createLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lesson/form";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/edit/{id}")
    public String editLesson(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id).orElse(null);
        model.addAttribute("lesson", lesson);
        return "lesson/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }

    @GetMapping("/filter")
    public String filterLessons(@RequestParam(required = false) String difficulty,
                                @RequestParam(required = false) Integer min,
                                @RequestParam(required = false) Integer max,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.filterLessons(difficulty, min, max, pageable);
        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("activeFilters", Map.of("difficulty", difficulty, "min", min, "max", max));
        return "lesson/list";
    }
}
