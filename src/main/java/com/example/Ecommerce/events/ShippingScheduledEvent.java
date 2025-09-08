package com.example.Ecommerce.events;

import java.time.LocalDate;
import com.example.Ecommerce.domain.EventType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShippingScheduledEvent extends Event {
    private final String orderId;
    private final LocalDate shippingDate;

    public ShippingScheduledEvent(String orderId, LocalDate shippingDate) {
        super(EventType.SHIPPING_SCHEDULED);
        this.orderId = orderId;
        this.shippingDate = shippingDate;
    }
}