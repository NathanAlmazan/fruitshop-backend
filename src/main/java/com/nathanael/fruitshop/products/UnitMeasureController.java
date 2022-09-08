package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitMeasureController implements EntityCrudController<UnitMeasureDto, String> {
    private final UnitMeasureServices unitMeasureServices;

    public UnitMeasureController(UnitMeasureServices unitMeasureServices) {
        this.unitMeasureServices = unitMeasureServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<UnitMeasureDto> create(@Valid @RequestBody UnitMeasureDto data) {
        return new ResponseEntity<>(unitMeasureServices.create(data), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<UnitMeasureDto> update(@Valid @RequestBody UnitMeasureDto data) {
        return new ResponseEntity<>(unitMeasureServices.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<UnitMeasureDto> delete(@PathVariable String id) {
        return new ResponseEntity<>(unitMeasureServices.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UnitMeasureDto> getById(@PathVariable String id, @RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(unitMeasureServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UnitMeasureDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(unitMeasureServices.getAll(additionalFields), HttpStatus.OK);
    }
}
