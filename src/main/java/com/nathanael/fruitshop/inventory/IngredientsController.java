package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController implements EntityCrudController<IngredientsDto, Long> {
    private final IngredientsServices ingredientsServices;

    public IngredientsController(IngredientsServices ingredientsServices) {
        this.ingredientsServices = ingredientsServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<IngredientsDto> create(@Valid @RequestBody IngredientsDto data) {
        return new ResponseEntity<>(ingredientsServices.create(data), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<IngredientsDto> update(@Valid @RequestBody IngredientsDto data) {
        return new ResponseEntity<>(ingredientsServices.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<IngredientsDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientsServices.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<IngredientsDto> getById(@PathVariable Long id, @RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(ingredientsServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<IngredientsDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(ingredientsServices.getAll(additionalFields), HttpStatus.OK);
    }
}
