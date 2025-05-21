package com.fernandopereira.desafio_anota_ai.domain;


import com.fernandopereira.desafio_anota_ai.domain.product.Product;
import com.fernandopereira.desafio_anota_ai.domain.product.ProductDTO;
import com.fernandopereira.desafio_anota_ai.domain.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService service;
    

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO) {
        Product newProduct = service.insert(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> product = this.service.getAll();
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestParam("id") String id, @RequestBody ProductDTO productDTO){
        Product product = this.service.update(id, productDTO);
        return  ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@RequestParam("id") String id){
        this.service.delete(id);
        return  ResponseEntity.noContent().build();
    }




}
