package com.example.Ecommerce.events;

import java.util.List;
import com.example.Ecommerce.domain.EventType;
import com.example.Ecommerce.domain.OrderItem;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderCreatedEvent extends Event {
    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;
    private final double totalAmount;

    public OrderCreatedEvent(String orderId, String customerId, List<OrderItem> items, double totalAmount) {
        super(EventType.ORDER_CREATED);
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }
}
