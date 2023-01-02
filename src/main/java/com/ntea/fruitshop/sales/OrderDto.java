package com.ntea.fruitshop.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long orderId;

    @NotNull(message = "Orders amount is required.")
    private Double totalAmount;

    @NotNull(message = "Payment type is required.")
    private String paymentType;

    @Length(max = 13)
    private String transactionId;

    private Boolean discounted;

    private Double paidAmount;

    private Boolean cancelled;

    @NotNull(message = "Orders items is required.")
    @Valid
    private List<OrderItemDto> orderItems;

    private LocalDateTime timestamp;
}
