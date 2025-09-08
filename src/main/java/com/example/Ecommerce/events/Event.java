package com.example.Ecommerce.events;

import java.util.UUID;
import java.time.LocalDateTime;
import com.example.Ecommerce.domain.EventType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "OrderCreatedEvent"),
        @JsonSubTypes.Type(value = PaymentReceivedEvent.class, name = "PaymentReceivedEvent"),
        @JsonSubTypes.Type(value = ShippingScheduledEvent.class, name = "ShippingScheduledEvent"),
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "OrderCancelledEvent")
})
public abstract class Event {
    private final String eventId;
    private final LocalDateTime timestamp;
    private final EventType eventType;

    public Event(EventType eventType) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.eventType = eventType;
    }
}
