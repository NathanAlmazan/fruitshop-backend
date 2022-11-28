package com.ntea.fruitshop.sales;

import com.ntea.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory implements ModelFactory<Orders, OrderDto> {
    private final OrderItemFactory orderItemFactory;

    public OrderFactory(OrderItemFactory orderItemFactory) {
        this.orderItemFactory = orderItemFactory;
    }

    @Override
    public Orders requestToEntity(OrderDto request) {
        Orders orders = new Orders();
        orders.setTotalAmount(request.getTotalAmount());
        orders.setPaymentType(request.getPaymentType());
        orders.setTransactionId(request.getTransactionId());
        orders.setPaidAmount(request.getPaidAmount());
        orders.setCancelled(request.getCancelled());

        return orders;
    }

    @Override
    public OrderDto entityToResponse(Orders entity, List<String> additionalFields) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(entity.getOrderId());
        orderDto.setTotalAmount(entity.getTotalAmount());
        orderDto.setPaymentType(entity.getPaymentType());
        orderDto.setTransactionId(entity.getTransactionId());
        orderDto.setTimestamp(entity.getTimestamp());
        orderDto.setPaidAmount(entity.getPaidAmount());
        orderDto.setCancelled(entity.getCancelled());

        if (additionalFields != null) {
            if (additionalFields.remove("items")) {
                orderDto.setOrderItems(orderItemFactory.entityListToResponse(entity.getOrderItems(), additionalFields));
                additionalFields.add("items");
            }
        }

        return orderDto;
    }
}
