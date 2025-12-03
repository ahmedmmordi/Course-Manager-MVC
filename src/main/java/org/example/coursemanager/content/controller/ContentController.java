package org.example.coursemanager.content.controller;

import org.example.coursemanager.content.model.Content;
import org.example.coursemanager.content.model.DateRange;
import org.example.coursemanager.content.service.ContentService;
import org.example.coursemanager.lesson.service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;
    private final LessonService lessonService;
    public ContentController(ContentService contentService, LessonService lessonService) {
        this.contentService = contentService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public String listContents(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Content> contentPage = contentService.getAllContents(pageable);
        model.addAttribute("contentPage", contentPage);
        return "content/list";
    }

    @GetMapping("/create")
    public String createContent(Model model) {
        model.addAttribute("content", new Content());
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "content/form";
    }

    @PostMapping("/save")
    public String saveContent(@ModelAttribute("content") Content content) {
        contentService.saveContent(content);
        return "redirect:/contents";
    }

    @GetMapping("/edit/{id}")
    public String editContent(@PathVariable Long id, Model model) {
        Content content = contentService.getContentById(id).orElse(null);
        model.addAttribute("content", content);
        return "content/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return "redirect:/contents";
    }

    @GetMapping("/filter/date")
    public String filterContentByDate(@RequestParam DateRange range, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Content> contentPage = contentService.filterContentsByCreatedAt(range, pageable);
        model.addAttribute("contentPage", contentPage);
        model.addAttribute("filterType", "date");
        model.addAttribute("range", range);
        return "content/list";
    }
}
