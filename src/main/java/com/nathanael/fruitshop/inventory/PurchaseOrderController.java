package com.nathanael.fruitshop.inventory;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderController implements EntityCrudController<PurchaseOrderDto, Long> {
    private final PurchaseOrderServices purchaseOrderServices;

    public PurchaseOrderController(PurchaseOrderServices purchaseOrderServices) {
        this.purchaseOrderServices = purchaseOrderServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<PurchaseOrderDto> create(@Valid @RequestBody PurchaseOrderDto data) {
        return new ResponseEntity<>(purchaseOrderServices.create(data), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<PurchaseOrderDto> update(@Valid @RequestBody PurchaseOrderDto data) {
        return new ResponseEntity<>(purchaseOrderServices.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<PurchaseOrderDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(purchaseOrderServices.delete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaseOrderDto> getById(Long id, List<String> additionalFields) {
        return null;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<PurchaseOrderDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(purchaseOrderServices.getAll(additionalFields), HttpStatus.OK);
    }
}
