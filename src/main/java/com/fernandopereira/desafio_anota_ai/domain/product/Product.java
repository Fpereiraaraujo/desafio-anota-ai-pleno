package com.fernandopereira.desafio_anota_ai.domain.product;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private Long id;

    public String title;

    public String owner;

    public String category;

    public Integer price;

    public String description;

    public void setCategory(Category category) {
       this.category = category.toString();
    }

    public Product (ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.owner = productDTO.owner();
        this.price = productDTO.price();
    }

}
