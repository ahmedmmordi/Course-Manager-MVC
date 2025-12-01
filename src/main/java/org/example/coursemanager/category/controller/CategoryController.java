package org.example.coursemanager.category.controller;

import org.example.coursemanager.category.model.Category;
import org.example.coursemanager.category.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        model.addAttribute("categoryPage", categoryPage);
        return "category/list";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id).orElse(null);
        model.addAttribute("category", category);
        return "category/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/search")
    public String searchCategories(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<Category> categoryPage = categoryService.getCategoriesByName(name, page, size);
        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("searchName", name);
        return "category/list";
    }
}
