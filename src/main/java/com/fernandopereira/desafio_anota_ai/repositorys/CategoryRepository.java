package com.fernandopereira.desafio_anota_ai.repositorys;

import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
