package com.example.Ecommerce.events;

import com.example.Ecommerce.domain.EventType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderCancelledEvent extends Event {
    private final String orderId;
    private final String reason;

    public OrderCancelledEvent(String orderId, String reason) {
        super(EventType.ORDER_CANCELLED);
        this.orderId = orderId;
        this.reason = reason;
    }
}
