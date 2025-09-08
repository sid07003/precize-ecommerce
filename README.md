# 📦 Ecommerce Order Management System (Event-Driven)

A simple **event-driven e-commerce order management system** built in **Java**, demonstrating how orders can be managed using events and the **Observer pattern**.  

This project simulates how real-world order processing systems work — orders are created, payments are received, shipments are scheduled, cancellations happen, and external systems are notified automatically.  

---

## 🚀 Features

- ✅ **Create Orders** with one or multiple items  
- ✅ **Event-driven updates** (OrderCreated, PaymentReceived, ShippingScheduled, OrderCancelled)  
- ✅ **Event Logging** into `events.ndjson` file  
- ✅ **Observer mechanism** to notify external systems:  
  - `LoggerObserver` → Logs status changes to the console  
  - `AlertObserver` → Sends alerts for critical events like cancellation  
- ✅ **In-memory Order Store** using `Map<String, Order>`  
- ✅ **Extensible Design** → easy to add new events or observers  

---

## 🛠️ System Design

The project follows **Domain-Driven Design (DDD) + Event Sourcing Concepts**:

- **Domain Layer**  
  - `Order` → Represents an order with items, status, and history  
  - `OrderItem` → Represents individual items  

- **Events**  
  - `OrderCreatedEvent`  
  - `PaymentReceivedEvent`  
  - `ShippingScheduledEvent`  
  - `OrderCancelledEvent`  

- **Event Handling**  
  - Each event updates the order state (`CREATED`, `PAID`, `SHIPPED`, `CANCELLED`)  
  - All events are logged in `events.ndjson`  

- **Observers** (Observer Pattern)  
  - `LoggerObserver` → Logs every change  
  - `AlertObserver` → Triggers alerts on cancellations  

---

## ⚙️ Workflow Example

1. Customer places an order → `OrderCreatedEvent`  
2. Customer pays → `PaymentReceivedEvent`  
3. Order shipped → `ShippingScheduledEvent`  
4. Order cancelled → `OrderCancelledEvent`  

👉 Each step is logged in **JSON file** and notified to observers.  


## 🖥️ Example Run
Order created and logged: Order{id='order1', status='CREATED', ...}<br>
[Logger] Event applied: PaymentReceivedEvent for Order order1<br>
[Logger] Event applied: ShippingScheduledEvent for Order order2<br>
[Alert] Sending alert for Order order3: Status changed to CANCELLED<br>

