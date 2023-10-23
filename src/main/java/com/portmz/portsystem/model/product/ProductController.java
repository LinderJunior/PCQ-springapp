package com.portmz.portsystem.model.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody ProductDto dto) {
        dto = productService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        Product product = productService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
