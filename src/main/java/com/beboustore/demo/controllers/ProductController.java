package com.beboustore.demo.controllers;

import com.beboustore.demo.models.Product;
import com.beboustore.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping("/submit-product-data")
    public ResponseEntity<String> submitProductData(@RequestBody Product product) {

        productRepository.save(product);

        return ResponseEntity.ok("Produit ajouté");
    }

    @DeleteMapping("/delete-product-data")
    public ResponseEntity<String> deleteProductData(@RequestParam Integer id) {

        var productResult = productRepository.findById(id);
        if (productResult.isPresent())
        {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Produit supprimé");
        }

        return ResponseEntity.notFound().build();
    }
}
