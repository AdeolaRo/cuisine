package org.example.cuisine.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {

   private long idCategory;

   private String name;

   @NotNull
   @NotBlank
   private String description;

}
