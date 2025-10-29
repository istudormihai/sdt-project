package entities;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    private List<Order> orderHistory;

    public Buyer(String email, String username, String password) {
        super(email, username, password);
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public void updateProfile() {
        System.out.println("Buyer profile updated for: " + username);
    }

    public Order purchaseService(Service service, ServiceOffering offering, Seller seller) {
        System.out.println(username + " is purchasing service: " + service.getTitle());
        Order order = new Order(this.userId, seller.getUserId(), service.getServiceId(), offering);
        orderHistory.add(order);
        return order;
    }

    public Review leaveReview(Order order, int rating) {
        System.out.println(username + " is leaving a review with rating: " + rating);
        return new Review(order.getOrderId(), this.userId, order.getSellerId(), rating);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "username='" + username + '\'' +
                ", totalOrders=" + orderHistory.size() +
                '}';
    }
}