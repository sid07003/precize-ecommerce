package com.example.Ecommerce;

import com.example.Ecommerce.domain.Order;
import com.example.Ecommerce.domain.OrderItem;
import com.example.Ecommerce.events.*;
import com.example.Ecommerce.observer.AlertObserver;
import com.example.Ecommerce.observer.LoggerObserver;

import java.time.LocalDate;
import java.util.*;

public class EcommerceApplication {
	private final Map<String, Order> orderStore = new HashMap<>();

	public static void main(String[] args) {
		EcommerceApplication app = new EcommerceApplication();

		List<OrderItem> items1 = Arrays.asList(new OrderItem("item1", 2));
		app.createOrder("order1", "cust1", items1, 500.0);
		app.performEvent("order1", new PaymentReceivedEvent("order1", 500.0));
		app.performEvent("order1", new ShippingScheduledEvent("order1", LocalDate.now()));

		List<OrderItem> items2 = Arrays.asList(new OrderItem("item2", 1));
		app.createOrder("order2", "cust2", items2, 300.0);
		app.performEvent("order2", new PaymentReceivedEvent("order2", 300.0));
		app.performEvent("order2", new OrderCancelledEvent("order2", "Customer changed mind"));

		List<OrderItem> items3 = Arrays.asList(new OrderItem("item3", 5));
		app.createOrder("order3", "cust3", items3, 1000.0);
		app.performEvent("order3", new OrderCancelledEvent("order3", "Out of stock"));

		List<OrderItem> items4 = Arrays.asList(new OrderItem("item4", 3));
		app.createOrder("order4", "cust4", items4, 750.0);
		app.performEvent("order4", new PaymentReceivedEvent("order4", 750.0));
	}

	private void createOrder(String orderId, String customerId, List<OrderItem> items, double totalAmount) {
		Order order = new Order(orderId, customerId, items, totalAmount);
		order.addObserver(new LoggerObserver());
		order.addObserver(new AlertObserver());
		orderStore.put(orderId, order);

		Event orderCreatedEvent = new OrderCreatedEvent(orderId, customerId, items, totalAmount);
		order.applyEvent(orderCreatedEvent);
		EventLogger.logEvent(orderCreatedEvent);
	}

	private void performEvent(String orderId, Event event) {
		Order order = orderStore.get(orderId);
		if (order != null) {
			order.applyEvent(event);
			EventLogger.logEvent(event);
		} else {
			System.err.println("Order not found: " + orderId);
		}
	}

	private void printAllOrders() {
		System.out.println("\nAll Orders in the System:");
		for (Order order : orderStore.values()) {
			System.out.println(order);
		}
	}
}
