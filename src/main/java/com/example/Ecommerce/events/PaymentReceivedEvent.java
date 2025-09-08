package com.example.Ecommerce.events;

import com.example.Ecommerce.domain.EventType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentReceivedEvent extends Event {
    private final String orderId;
    private final double amountPaid;

    public PaymentReceivedEvent(String orderId, double amountPaid) {
        super(EventType.PAYMENT_RECEIVED);
        this.orderId = orderId;
        this.amountPaid = amountPaid;
    }
}
