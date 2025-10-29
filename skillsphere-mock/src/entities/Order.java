package entities;

import java.util.Date;

public class Order {
    private String orderId;
    private Long buyerId;
    private Long sellerId;
    private String serviceId;
    private ServiceOffering offering;
    private double totalPrice;
    private Date orderDate;
    private OrderStatus status;
    private Date deliveryDate;

    public Order(Long buyerId, Long sellerId, String serviceId, ServiceOffering offering) {
        this.orderId = "ORD-" + System.currentTimeMillis();
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.serviceId = serviceId;
        this.offering = offering;
        this.orderDate = new Date();
        this.status = OrderStatus.PENDING;
        this.totalPrice = calculateTotalPrice();

        System.out.println("Order created: " + orderId);
    }

    public double calculateTotalPrice() {
        double price = offering.getPrice();
        System.out.println("Calculated total price: $" + price);
        return price;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to: " + newStatus);

        if (newStatus == OrderStatus.COMPLETED) {
            System.out.println("Order completed successfully!");
        }
    }

    public String getOrderId() { return orderId; }
    public Long getBuyerId() { return buyerId; }
    public Long getSellerId() { return sellerId; }
    public String getServiceId() { return serviceId; }
    public ServiceOffering getOffering() { return offering; }
    public double getTotalPrice() { return totalPrice; }
    public Date getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }
    public Date getDeliveryDate() { return deliveryDate; }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", totalPrice=$" + totalPrice +
                ", status=" + status +
                ", deliveryTime=" + offering.getDeliveryTime() + " days" +
                '}';
    }
}