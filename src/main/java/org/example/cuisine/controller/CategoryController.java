package org.example.cuisine.controller;

import jakarta.validation.Valid;
import org.example.cuisine.model.Category;
import org.example.cuisine.model.Recipe;
import org.example.cuisine.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//        @GetMapping("/")
//        public String homePage(){
//            return "home";
//        }

        @RequestMapping("/category/list")
        public String listCategory(Model model){
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "/category/list";
        }

        @GetMapping("/category/form")
        public String addCategory(Model model) {
            model.addAttribute("category", new Category());
            return "category/form";
        }

        @PostMapping("/category/form")
        public String submitCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return "category/form";
            }
            categoryService.addCategory(category);
            return "redirect:/category/list";
        }

        @GetMapping("/category/update/{id}")
        public String updateCategoryForm(@PathVariable Long id, Model model) {
            Category category = categoryService.getCategoryById(id);
            if (category != null) {
                model.addAttribute("category", category);
                return "category/form";
            } else {
                return "redirect:/category/list";
            }
        }

        @PostMapping("/category/update")
        public String updateCategory(@ModelAttribute("category") Category category) {
            categoryService.updateCategory(category);
            return "redirect:/category/list";
        }

        @GetMapping("/category/delete/{id}")
        public String deleteCategory(@PathVariable Long id) {
            categoryService.deleteCategoryById(id);
            return "redirect:/category/list";
        }

    }







