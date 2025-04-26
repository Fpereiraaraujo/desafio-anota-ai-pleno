package com.fernandopereira.desafio_anota_ai.domain;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.CategoryDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.CategoryService;
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
        return ResponseEntity.ok(newCategory);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok(categories);
    }
}
