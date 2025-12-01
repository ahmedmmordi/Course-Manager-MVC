package org.example.coursemanager.lesson.controller;

import org.example.coursemanager.lesson.model.Lesson;
import org.example.coursemanager.lesson.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public String listLessons(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.getAllLessons(pageable);
        model.addAttribute("lessonPage", lessonPage);
        return "lesson/list";
    }

    @GetMapping("/filter/difficulty")
    public String filterLessonByDifficulty(@RequestParam String difficulty, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.filterLessonsByDifficulty(difficulty, pageable);
        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("filterType", "difficulty");
        model.addAttribute("filterValue", difficulty);
        return "lesson/list";
    }

    @GetMapping("/filter/duration")
    public String filterLessonByDuration(@RequestParam Integer min, @RequestParam Integer max, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.filterLessonsByDuration(min, max, pageable);
        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("filterType", "duration");
        model.addAttribute("min", min);
        model.addAttribute("max", max);
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
}
