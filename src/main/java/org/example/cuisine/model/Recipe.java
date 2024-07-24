package org.example.cuisine.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {


    private long id;
    private long idCategory;

    @NotBlank
    private String name;


    private Category category;


    @NotNull
    List<String> ingredients;
    @NotNull
    String instructions;



}
