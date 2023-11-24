package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.example.demo.services.ProductService;
import com.example.demo.entities.Product;
import com.example.demo.models.ProductDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable final Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.search(query));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable final Long categoryId) {
        return ResponseEntity.ok(productService.findByCategory(categoryId));
    }

    @GetMapping("/color/{colorId}")
    public ResponseEntity<List<Product>> getProductsByColor(@PathVariable final Long colorId) {
        return ResponseEntity.ok(productService.findByColor(colorId));
    }

    // @GetMapping("/size/{sizeId}")
    // public ResponseEntity<List<Product>> getProductsBySize(@PathVariable final Long sizeId) {
    //     return ResponseEntity.ok(productService.findBySize(sizeId));
    // }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProduct(@RequestBody @Valid final ProductDTO productDTO) {
        return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long id, @RequestBody @Valid final ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
