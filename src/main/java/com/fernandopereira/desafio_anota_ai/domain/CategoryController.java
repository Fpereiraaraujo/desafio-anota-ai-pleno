package com.fernandopereira.desafio_anota_ai.domain;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.CategoryDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = service.insert(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO categoryDTO){
        Category category = this.service.update(id, categoryDTO);
        return  ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathParam("id") String id){
        this.service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
