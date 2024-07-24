package org.example.cuisine.model;

import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
   private long idCategory;
   private String name;
   private String description;

}
