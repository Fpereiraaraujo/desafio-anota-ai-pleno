package com.fernandopereira.desafio_anota_ai.domain.services;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.CategoryDTO;
import com.fernandopereira.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernandopereira.desafio_anota_ai.repositorys.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.repository.findById(id)
                .orElseThrow(
                        CategoryNotFoundException::new);


        return category;
    }


    public void delete(String id) {
        Category category = this.repository.findById(id)
                .orElseThrow(
                        CategoryNotFoundException::new);
        this.repository.delete(category);
    }

}

