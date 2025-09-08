package com.example.Ecommerce.observer;

import com.example.Ecommerce.domain.Order;
import com.example.Ecommerce.events.Event;
import com.example.Ecommerce.domain.OrderStatus;

public class AlertObserver implements OrderObserver {
    @Override
    public void update(Order order, Event event) {
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.SHIPPED) {
            System.out.println("[ALERT] Sending alert for Order " + order.getOrderId() + ": Status changed to " + order.getStatus());
        }
    }
}
