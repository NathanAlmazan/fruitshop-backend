package com.nathanael.fruitshop.inventory;

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
public class PurchaseOrderDto {

    private Long purchaseId;

    @NotNull(message = "Supplier initials is required.")
    @Length(min = 1, max = 30)
    private String supplier;

    @NotNull(message = "Total purchase order is required.")
    private Double totalPrice;

    private Boolean paid = false;

    private Boolean arrived = false;

    @NotNull(message = "Purchase receipt or invoice is required.")
    private String receiptURL;

    private LocalDateTime dueDate;

    private LocalDateTime purchaseDate;

    @NotNull(message = "Purchased items are required.")
    @Valid
    private List<PurchasedItemsDto> purchasedItems;

}
