package com.fernandopereira.desafio_anota_ai.domain.services;
import com.fernandopereira.desafio_anota_ai.domain.category.Category;
import com.fernandopereira.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernandopereira.desafio_anota_ai.domain.product.Product;
import com.fernandopereira.desafio_anota_ai.domain.product.ProductDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.aws.AwsSnsService;
import com.fernandopereira.desafio_anota_ai.domain.services.aws.MessageDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.exceptions.ProductNotFoundException;
import com.fernandopereira.desafio_anota_ai.repositorys.CategoryRepository;
import com.fernandopereira.desafio_anota_ai.repositorys.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AwsSnsService snsService;

    @Autowired
    private CategoryService categoryService;


    public Product insert(ProductDTO productDTO) {
        this.categoryRepository.findById(productDTO.category()).orElseThrow(
                CategoryNotFoundException::new
        );
        Product newProduct = new Product(productDTO);

        this.productRepository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.toString()));

        return newProduct;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        ProductNotFoundException::new);

        if(productDTO.category() != null){
            this.categoryService.getById(productDTO.category())
                    .orElseThrow(
                            CategoryNotFoundException::new);
            product.setCategory(productDTO.category());

        }
        if(productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if(productDTO.description().isEmpty()) product.setDescription(productDTO.title());
        if(productDTO.price() == null) product.setPrice(productDTO.price());

        this.snsService.publish(new MessageDTO(product.toString()));
        return this.productRepository.save(product);


    }


    public void delete(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(
                        ProductNotFoundException::new);
        this.productRepository.delete(product);
    }
}
