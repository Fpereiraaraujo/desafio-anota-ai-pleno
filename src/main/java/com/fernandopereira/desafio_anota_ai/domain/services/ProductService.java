package com.fernandopereira.desafio_anota_ai.domain.services;
import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernandopereira.desafio_anota_ai.domain.product.Product;
import com.fernandopereira.desafio_anota_ai.domain.product.ProductDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.exceptions.ProductNotFoundException;
import com.fernandopereira.desafio_anota_ai.repositorys.CategoryRepository;
import com.fernandopereira.desafio_anota_ai.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public Product insert(ProductDTO productDTO) {
        Category category = this.categoryRepository.findById(productDTO.category()).orElseThrow(
                CategoryNotFoundException::new
        );

        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        ProductNotFoundException::new);


        return product;
    }


    public void delete(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        ProductNotFoundException::new);
        this.productRepository.delete(product);
    }
}
