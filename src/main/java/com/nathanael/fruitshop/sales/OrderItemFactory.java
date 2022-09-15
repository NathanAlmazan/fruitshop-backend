package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.global.ModelFactory;
import com.nathanael.fruitshop.products.ProductServices;
import com.nathanael.fruitshop.products.UnitMeasureServices;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemFactory implements ModelFactory<OrderItems, OrderItemDto> {
    private final ProductServices productServices;
    private final OrderServices orderServices;
    private final UnitMeasureServices unitMeasureServices;

    public OrderItemFactory(@Lazy ProductServices productServices, @Lazy OrderServices orderServices, UnitMeasureServices unitMeasureServices) {
        this.productServices = productServices;
        this.orderServices = orderServices;
        this.unitMeasureServices = unitMeasureServices;
    }

    @Override
    public OrderItems requestToEntity(OrderItemDto request) {
        OrderItemKey key = new OrderItemKey(request.getOrderId(), request.getProductCode(), request.getUnitCode());
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemKey(key);
        orderItems.setQuantity(request.getQuantity());
        orderItems.setProduct(productServices.getById(request.getProductCode()));
        orderItems.setOrders(orderServices.getById(request.getOrderId()));
        orderItems.setUnitType(unitMeasureServices.getById(request.getUnitCode()));

        return orderItems;
    }

    @Override
    public OrderItemDto entityToResponse(OrderItems entity, List<String> additionalFields) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderId(entity.getOrderItemKey().getOrderId());
        orderItemDto.setProductCode(entity.getOrderItemKey().getProductCode());
        orderItemDto.setQuantity(entity.getQuantity());
        orderItemDto.setUnitCode(entity.getOrderItemKey().getTypeCode());

        if (additionalFields != null && additionalFields.remove("product")) {
            orderItemDto.setProduct(productServices.getDtoById(entity.getOrderItemKey().getProductCode(), additionalFields));
            additionalFields.add("product");
        }

        return orderItemDto;
    }
}
