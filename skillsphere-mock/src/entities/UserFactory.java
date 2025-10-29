package entities;

public class UserFactory {

    public static User createUser(String userType, String email, String username, String password) {
        if (userType == null) {
            throw new IllegalArgumentException("User type cannot be null");
        }

        switch (userType.toLowerCase()) {
            case "buyer":
                System.out.println("Creating Buyer account...");
                return new Buyer(email, username, password);

            case "seller":
                System.out.println("Creating Seller account...");
                return new Seller(email, username, password);

            default:
                throw new IllegalArgumentException("Unknown user type: " + userType);
        }
    }
}