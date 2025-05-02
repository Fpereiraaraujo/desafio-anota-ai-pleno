package com.fernandopereira.desafio_anota_ai.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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

    public String getOwnerId() {
        return ownerId;
    }

    public String ownerId;

    public String category;

    public Integer price;

    public String description;

    public void setCategory(String category) {
       this.category = category.toString();
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product (ProductDTO productDTO){
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.category = productDTO.category();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("id", this.id);
        json.put("category", this.category);
        json.put("price", this.price);
        json.put("type", "product");

        return json.toString();
    }

}
