package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController implements EntityCrudController<OrderDto, Long> {
    private final OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto data) {
        return new ResponseEntity<>(orderServices.create(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderDto> update(OrderDto data) {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> getById(Long id, List<String> additionalFields) {
        return null;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(orderServices.getAll(additionalFields), HttpStatus.OK);
    }
}
