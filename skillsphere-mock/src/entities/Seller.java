package entities;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private List<String> portfolio;
    private List<String> skills;
    private double averageRating;
    private int totalReviews;
    private List<Service> services;

    public Seller(String email, String username, String password) {
        super(email, username, password);
        this.portfolio = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.services = new ArrayList<>();
        this.averageRating = 0.0;
        this.totalReviews = 0;
    }

    @Override
    public void updateProfile() {
        System.out.println("Seller profile updated for: " + username);
    }

    public ServiceBuilder createService() {
        System.out.println(username + " is creating a new service...");
        return new ServiceBuilder(this.userId);
    }

    public void addService(Service service) {
        services.add(service);
        System.out.println("Service '" + service.getTitle() + "' added to seller's portfolio.");
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public void addPortfolioItem(String item) {
        portfolio.add(item);
    }

    public List<Service> getServices() {
        return services;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void updateRating(int newRating) {
        averageRating = ((averageRating * totalReviews) + newRating) / (totalReviews + 1);
        totalReviews++;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "username='" + username + '\'' +
                ", services=" + services.size() +
                ", averageRating=" + String.format("%.1f", averageRating) +
                ", totalReviews=" + totalReviews +
                '}';
    }
}