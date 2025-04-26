package com.fernandopereira.desafio_anota_ai.repositorys;

import com.fernandopereira.desafio_anota_ai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
