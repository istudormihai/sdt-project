package entities;

import java.util.Date;

public abstract class User {
    protected Long userId;
    protected String email;
    protected String username;
    protected String password;
    protected Date registrationDate;

    public User(String email, String username, String password) {
        this.userId = System.currentTimeMillis(); // Simple ID generation
        this.email = email;
        this.username = username;
        this.password = password;
        this.registrationDate = new Date();
    }

    public boolean login() {
        System.out.println(username + " logged in successfully.");
        return true;
    }

    public void logout() {
        System.out.println(username + " logged out.");
    }

    public abstract void updateProfile();

    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}