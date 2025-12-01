package org.example.coursemanager.course.controller;

import org.example.coursemanager.course.model.Course;
import org.example.coursemanager.course.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseService.getAllCourses(pageable);
        model.addAttribute("coursePage", coursePage);
        return "course/list";
    }

    @GetMapping("/create")
    public String createCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id).orElse(null);
        model.addAttribute("course", course);
        return "course/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
