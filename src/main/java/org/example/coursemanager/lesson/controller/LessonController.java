package org.example.coursemanager.lesson.controller;

import org.example.coursemanager.category.service.CategoryService;
import org.example.coursemanager.course.model.Course;
import org.example.coursemanager.course.service.CourseService;
import org.example.coursemanager.lesson.model.Lesson;
import org.example.coursemanager.lesson.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final CategoryService categoryService;
    private final CourseService courseService;
    public LessonController(LessonService lessonService, CategoryService categoryService, CourseService courseService) {
        this.lessonService = lessonService;
        this.categoryService = categoryService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listLessons(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.getAllLessons(pageable);
        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("activeFilters", Map.of("difficulty", "","min", "", "max", ""));
        return "lesson/list";
    }

    @GetMapping("/create")
    public String createLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "lesson/form";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson) {
        if (lesson.getCourse() != null && lesson.getCourse().getId() != null) {
            Course managedCourse = courseService.getCourseById(lesson.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course Not Found."));
            lesson.setCourse(managedCourse);
        }
        lessonService.saveLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/edit/{id}")
    public String editLesson(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id)
                .orElseThrow(() -> new RuntimeException("Lesson Not Found."));
        model.addAttribute("lesson", lesson);
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "lesson/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }

    @GetMapping("/filter")
    public String filterLessons(@RequestParam(required = false) String difficulty, @RequestParam(required = false) Integer max, @RequestParam(required = false) Boolean published, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        if (difficulty != null && difficulty.isBlank()) {
            difficulty = null;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = lessonService.filterLessons(difficulty, max, published, pageable);

        Map<String, Object> activeFilters = new HashMap<>();
        activeFilters.put("published", published);
        activeFilters.put("difficulty", difficulty);
        activeFilters.put("max", max);

        model.addAttribute("lessonPage", lessonPage);
        model.addAttribute("activeFilters", activeFilters);
        return "lesson/list";
    }
}
