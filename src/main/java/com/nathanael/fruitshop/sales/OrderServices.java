package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices implements EntityCrudServices<Orders, OrderDto, Long> {
    private final OrderRepo orderRepo;
    private final OrderItemsRepo orderItemsRepo;
    private final OrderFactory orderFactory;
    private final OrderItemFactory orderItemFactory;

    public OrderServices(OrderRepo orderRepo, OrderItemsRepo orderItemsRepo, OrderFactory orderFactory, OrderItemFactory orderItemFactory) {
        this.orderRepo = orderRepo;
        this.orderItemsRepo = orderItemsRepo;
        this.orderFactory = orderFactory;
        this.orderItemFactory = orderItemFactory;
    }

    @Override
    public OrderDto create(OrderDto data) {
        Orders orders = orderRepo.save(orderFactory.requestToEntity(data));

        data.getOrderItems().forEach(item -> {
            item.setOrderId(orders.getOrderId());
            OrderItems orderItem = orderItemFactory.requestToEntity(item);
            orderItemsRepo.save(orderItem);
        });

        return orderFactory.entityToResponse(orders, null);
    }

    @Override
    public OrderDto update(OrderDto data) {
        return null;
    }

    @Override
    public OrderDto delete(Long id) {
        return null;
    }

    @Override
    public OrderDto getDtoById(Long id, List<String> additionalFields) {
        return orderFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public Orders getById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Order with id of " + id + " not found.");
        });
    }

    @Override
    public boolean entityExists(Long id) {
        return false;
    }

    @Override
    public List<OrderDto> getAll(List<String> additionalFields) {
        return orderFactory.entityListToResponse(orderRepo.findAll(), additionalFields);
    }
}
