package entities;

public class Review {
    private String reviewId;
    private String orderId;
    private Long buyerId;
    private Long sellerId;
    private int rating;

    public Review(String orderId, Long buyerId, Long sellerId, int rating) {
        this.reviewId = "REV-" + System.currentTimeMillis();
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.rating = Math.max(1, Math.min(5, rating)); // Ensure rating is 1-5

        System.out.println("Review created with rating: " + this.rating + "/5");
    }

    public String getReviewSummary() {
        String stars = "⭐".repeat(rating);
        return "Review " + reviewId + ": " + stars + " (" + rating + "/5)";
    }

    public String getReviewId() { return reviewId; }
    public String getOrderId() { return orderId; }
    public Long getBuyerId() { return buyerId; }
    public Long getSellerId() { return sellerId; }
    public int getRating() { return rating; }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", rating=" + rating + "/5" +
                '}';
    }
}