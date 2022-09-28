package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController implements EntityCrudController<ProductDto, String> {
    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto data) {
        return new ResponseEntity<>(productServices.create(data), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductDto data) {
        return new ResponseEntity<>(productServices.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ProductDto> delete(@PathVariable String id) {
        return new ResponseEntity<>(productServices.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProductDto> getById(@PathVariable String id, @RequestParam List<String> additionalFields) {
        return new ResponseEntity<>(productServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ProductDto>> getAll(@RequestParam List<String> additionalFields) {
        return new ResponseEntity<>(productServices.getAll(additionalFields), HttpStatus.OK);
    }
}
