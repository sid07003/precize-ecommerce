package com.example.Ecommerce.domain;

import com.example.Ecommerce.events.*;
import com.example.Ecommerce.observer.OrderObserver;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Order {
    private String orderId;
    private String customerId;
    private List<OrderItem> items = new ArrayList<>();
    private double totalAmount;
    private OrderStatus status = OrderStatus.PENDING;
    private List<String> eventHistory = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(String orderId, String customerId, List<OrderItem> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Event event) {
        for (OrderObserver observer : observers) {
            observer.update(this, event);
        }
    }

    public void addEvent(String eventDescription) {
        this.eventHistory.add(eventDescription);
    }

    public void applyEvent(Event event) {
        if(event instanceof OrderCreatedEvent) {
            this.status = OrderStatus.PENDING;
        } else if(event instanceof PaymentReceivedEvent) {
            this.status = OrderStatus.PAID;
        } else if(event instanceof ShippingScheduledEvent) {
            this.status = OrderStatus.SHIPPED;
        } else if(event instanceof OrderCancelledEvent) {
            this.status = OrderStatus.CANCELLED;
        }
        this.addEvent(event.getEventType().name());
        notifyObservers(event);
    }
}
