package org.example.cuisine.service;

import lombok.Getter;
import org.example.cuisine.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Getter
    List<Category> categories;
    int id = 1;
    Category category;

    public CategoryService() {
        categories = new ArrayList<>();
        categories.add(new Category(1,"plat","plat" ));
        categories.add(new Category(2,"dessert","dessert" ));
        categories.add(new Category(3,"resistance","resistance" ));
        categories.add(new Category(4,"encas","encas" ));

    }

    public Category addCategory(Category category) {
        Category category1 = new Category();
        category1.setIdCategory(id++);
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categories.add(category1);
        return category;
    }

    public Category getCategoryById(long id) {
        return categories.stream().filter(category1 -> category1.getIdCategory() == id).findFirst().orElse(null);
    }

    public Category getCategoryByName(String name) {
        return categories.stream().filter(recipe1 -> recipe1.getName().equals(name)).findFirst().orElse(null);
    }

    public void updateCategory(Category category) {
        Category category1 = new Category();
        category1.setIdCategory(id);
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categories.add(category1);
    }

    public void deleteCategoryById(long id) {
        Category category = getCategoryById(id);
        categories.remove(category);
    }

    public void deleteCategoryByName(String name) {
        Category category = getCategoryByName(name);
        categories.remove(category);
    }

    public List<Category> getAllCategories() {
        return categories;
    }


}
