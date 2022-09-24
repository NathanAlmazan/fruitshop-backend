package com.nathanael.fruitshop.sales;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import com.nathanael.fruitshop.products.ProductDto;
import com.nathanael.fruitshop.products.ProductServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices implements EntityCrudServices<Orders, OrderDto, Long> {
    private final OrderRepo orderRepo;
    private final OrderItemsRepo orderItemsRepo;
    private final OrderFactory orderFactory;
    private final OrderItemFactory orderItemFactory;
    private final ProductServices productServices;

    public OrderServices(OrderRepo orderRepo, OrderItemsRepo orderItemsRepo, OrderFactory orderFactory, OrderItemFactory orderItemFactory, ProductServices productServices) {
        this.orderRepo = orderRepo;
        this.orderItemsRepo = orderItemsRepo;
        this.orderFactory = orderFactory;
        this.orderItemFactory = orderItemFactory;
        this.productServices = productServices;
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
        Orders orders = orderRepo.findById(data.getOrderId())
                .map(order -> {
                    order.setCancelled(data.getCancelled());

                    return orderRepo.save(order);
                })
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Order with id of " + data.getOrderId() + " not found.");
                });

        return orderFactory.entityToResponse(orders, null);
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

    public List<DailySalesReport> getDailySalesReport() {
        List<DailySalesCount> dailySalesCountList = orderRepo.getDailySalesCount();
        List<DailySalesReport> dailySalesReportList = new ArrayList<>();

        dailySalesCountList.forEach(report -> dailySalesReportList.add(new DailySalesReport(report.getReportMonth(), report.getReportDate(), report.getTotalSales())));

        return dailySalesReportList;
    }

    public List<OrderItemStatistics> getOrderItemStatistics() {
        List<OrderItemStatistics> statisticsList = new ArrayList<>();
        Long rowCount = orderItemsRepo.getOrderItemsCount().getEntityCount();

        orderItemsRepo.getGroupByProduct().forEach(item -> {
            ProductDto product = productServices.getDtoById(item.getProductCode(), null);
            Double productPercentage = (double) item.getProductCount() / (double) rowCount;

            statisticsList.add(new OrderItemStatistics(product, productPercentage));
        });

        return statisticsList;
    }
}
