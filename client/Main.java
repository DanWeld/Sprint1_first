package client;

import clientModel.User;
import clientModel.Property;
import clientModel.Booking;
import clientModel.Payment;
import clientModel.Admin;

public class Main {
    public static void main(String[] args) {
        // Initialize the application
        System.out.println("Summer House Booking System Starting...");
        
        // Example usage
        User user = new User("testUser", "test@example.com", "password123");
        System.out.println("User created: " + user.getUsername());
    }
} 