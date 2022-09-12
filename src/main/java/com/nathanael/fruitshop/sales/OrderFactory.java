package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.global.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory implements ModelFactory<Orders, OrderDto> {
    @Override
    public Orders requestToEntity(OrderDto request) {
        Orders orders = new Orders();
        orders.setTotalAmount(request.getTotalAmount());
        orders.setPaymentType(request.getPaymentType());
        orders.setTransactionId(request.getTransactionId());

        return orders;
    }

    @Override
    public OrderDto entityToResponse(Orders entity, List<String> additionalFields) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(entity.getOrderId());
        orderDto.setTotalAmount(entity.getTotalAmount());
        orderDto.setPaymentType(entity.getPaymentType());
        orderDto.setTransactionId(entity.getTransactionId());

        return orderDto;
    }
}
