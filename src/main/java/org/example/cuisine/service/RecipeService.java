package org.example.cuisine.service;

import lombok.Getter;
import org.example.cuisine.model.Category;
import org.example.cuisine.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    List<Recipe> recipes;
    long id = 1;
    Category category;

    CategoryService categoryService;



    public RecipeService() {
        recipes = new ArrayList<>();
        categoryService = new CategoryService();
    }

    public List<Recipe> getAllRecipes(){

        return recipes;
    }

    public Recipe addRecipe(Recipe recipe) {
        Recipe recipe1 = new Recipe();
        recipe1.setId(id++);
        recipe1.setName(recipe.getName());
        recipe1.setInstructions(recipe.getInstructions());
        recipe1.setIngredients(recipe.getIngredients());

        recipe1.setCategory(categoryService.getCategoryById(recipe.getIdCategory()));
        recipes.add(recipe1);
        System.out.println(recipe1);
        return recipe1;
    }

    public Recipe getRecipeById(long id) {
        return recipes.stream().filter(recipe1 -> recipe1.getId() == id).findFirst().orElse(null);
    }

    public Recipe getRecipeByName(String name) {
        return recipes.stream().filter(recipe1 -> recipe1.getName().equals(name)).findFirst().orElse(null);
    }

    public void updateRecipe( Recipe recipe) {
        Recipe recipe1 = new Recipe();
        recipe1.setName(recipe.getName());
        recipe1.setCategory(category);
        recipes.add(recipe1);
    }

    public void deleteRecipeById(long id) {
        Recipe recipe = getRecipeById(id);
        recipes.remove(recipe);
    }

}
