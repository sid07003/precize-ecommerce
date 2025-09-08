package com.example.Ecommerce.observer;

import com.example.Ecommerce.domain.Order;
import com.example.Ecommerce.events.Event;

public interface OrderObserver {
    void update(Order order, Event event);
}
