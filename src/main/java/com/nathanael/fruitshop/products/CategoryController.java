package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController implements EntityCrudController<CategoryDto, Long> {
    private final CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto data) {
        return new ResponseEntity<>(categoryServices.create(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryDto> update(CategoryDto data, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryDto> delete(Long id) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id, @RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(categoryServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<CategoryDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(categoryServices.getAll(additionalFields), HttpStatus.OK);
    }
}
