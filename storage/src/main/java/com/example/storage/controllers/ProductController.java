package com.example.storage.controllers;

import com.example.order.models.Order;
import com.example.storage.models.Product;
import com.example.storage.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/storage")
public class ProductController {
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false, defaultValue = "id")
                                                            String sort) {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getById(id));
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Void> buyProduct(@PathVariable("id") Long id,
                                                 @RequestBody Order order) {
        productService.reduceAmount(id, order);
        return ResponseEntity.ok().body(null);
    }


}
