package com.example.Ecommerce.observer;

import com.example.Ecommerce.domain.Order;
import com.example.Ecommerce.events.Event;

public class LoggerObserver implements OrderObserver {
    @Override
    public void update(Order order, Event event) {
        System.out.println("[LOGGER] Event applied: " + event + " | New Order Status: " + order.getStatus());
    }
}
