package com.fernandopereira.desafio_anota_ai.domain;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.CategoryDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.CategoryService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {


    @Autowired
    private CategoryService service;

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
    public ResponseEntity<Category> update(@RequestParam("id") String id, @RequestBody CategoryDTO categoryDTO){
        Category category = this.service.update(id, categoryDTO);
        return  ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@RequestParam("id") String id){
        this.service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
