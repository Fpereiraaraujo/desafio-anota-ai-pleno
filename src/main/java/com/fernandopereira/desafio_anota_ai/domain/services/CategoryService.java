package com.fernandopereira.desafio_anota_ai.domain.services;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.CategoryDTO;
import com.fernandopereira.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernandopereira.desafio_anota_ai.domain.services.aws.AwsSnsService;
import com.fernandopereira.desafio_anota_ai.domain.services.aws.MessageDTO;
import com.fernandopereira.desafio_anota_ai.repositorys.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        this.snsService.publish(new MessageDTO(newCategory.toString()));
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
        if(!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        this.repository.save(category);

        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }


    public void delete(String id) {
        Category category = this.repository.findById(id)
                .orElseThrow(
                        CategoryNotFoundException::new);
        this.repository.delete(category);
    }

}

