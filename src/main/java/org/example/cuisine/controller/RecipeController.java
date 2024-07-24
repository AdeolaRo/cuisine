package org.example.cuisine.controller;

import jakarta.validation.Valid;
import org.example.cuisine.model.Category;
import org.example.cuisine.model.Recipe;
import org.example.cuisine.service.CategoryService;
import org.example.cuisine.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecipeController {

    private final CategoryService categoryService;
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @RequestMapping("/list")
    public String listRecipes(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "list";
    }

    @GetMapping("/form")
    public String addRecipe(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categories);
        return "form";
    }

    @PostMapping("/form")
    public String submitRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form";
        }
        recipeService.addRecipe(recipe);
        return "redirect:/list";
    }

    @GetMapping("/search/{id}")
    public String showRecipe(@PathVariable long id , Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "detail";
        } else {
            return "home";
        }
    }

    @GetMapping("/search")
    public String showRecipe(@RequestParam(value = "recipeName") String name, Model model) {
        name = name.trim();
        Recipe recipe = recipeService.getRecipeByName(name);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "detail";
        }
        return "/error/404";
    }

    @GetMapping("/update/{id}")
    public String updateRecipeForm(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "form";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute("recipe") Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/list";
    }




}
