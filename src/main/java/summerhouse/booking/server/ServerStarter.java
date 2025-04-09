package summerhouse.booking.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import summerhouse.booking.shared.network.ISummerhouseService;

public class ServerStarter {
    private static final int PORT = 1099;
    private static final String SERVICE_NAME = "SummerhouseService";

    public static void main(String[] args) {
        try {
            // Create and export the service
            final SummerhouseServiceImpl service = new SummerhouseServiceImpl();
            final ISummerhouseService stub = (ISummerhouseService) 
                UnicastRemoteObject.exportObject(service, 0);

            // Create or get the registry
            final Registry registry = createOrGetRegistry();
            
            // Bind the service
            registry.rebind(SERVICE_NAME, stub);
            System.out.println("Server is running on port " + PORT);

            // Add shutdown hook for clean server shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    registry.unbind(SERVICE_NAME);
                    UnicastRemoteObject.unexportObject(service, true);
                    UnicastRemoteObject.unexportObject(registry, true);
                    System.out.println("Server shut down successfully");
                } catch (Exception e) {
                    System.err.println("Error during server shutdown: " + e.getMessage());
                }
            }));

        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Registry createOrGetRegistry() {
        try {
            // Try to create a new registry
            Registry registry = LocateRegistry.createRegistry(PORT);
            System.out.println("Created new RMI registry on port " + PORT);
            return registry;
        } catch (Exception e) {
            // If creation fails, try to get existing registry
            try {
                Registry registry = LocateRegistry.getRegistry(PORT);
                System.out.println("Using existing RMI registry on port " + PORT);
                return registry;
            } catch (Exception ex) {
                System.err.println("Failed to create or get RMI registry: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }
} 