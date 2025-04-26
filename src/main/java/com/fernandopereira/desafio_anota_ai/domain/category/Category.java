package com.fernandopereira.desafio_anota_ai.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    public String id;

    public String title;

    public String owner;

    public String description;

    public Category(CategoryDTO categoryDTO){
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.owner = categoryDTO.owner();
    }

}
