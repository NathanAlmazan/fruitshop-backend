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

    @PutMapping
    @Override
    public ResponseEntity<OrderDto> update(@Valid @RequestBody OrderDto data) {
        return new ResponseEntity<>(orderServices.update(data), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> delete(Long id) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrderDto> getById(@PathVariable Long id, @RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(orderServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(orderServices.getAll(additionalFields), HttpStatus.OK);
    }

    @GetMapping("/statistics/product")
    public ResponseEntity<List<OrderItemStatistics>> getOrderItemsStatistics() {
        return new ResponseEntity<>(orderServices.getOrderItemStatistics(), HttpStatus.OK);
    }

    @GetMapping("/statistics/sales")
    public ResponseEntity<List<DailySalesReport>> getDailySalesReport() {
        return new ResponseEntity<>(orderServices.getDailySalesReport(), HttpStatus.OK);
    }
}
