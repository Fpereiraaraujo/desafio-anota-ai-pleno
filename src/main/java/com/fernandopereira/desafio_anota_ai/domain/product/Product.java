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

    private String title;

    private String owner;

    private String category;

    private Integer price;

    private String description;




    public Product (ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.owner = productDTO.owner();
        this.price = productDTO.price();
    }

}
