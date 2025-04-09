package summerhouse.booking.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import summerhouse.booking.shared.network.ISummerhouseService;

public class RMIServer {
    private static final int PORT = 1099;
    private Registry registry;
    private ISummerhouseService summerhouseService;

    public RMIServer() {
        try {
            // Create and export the service on the default port
            summerhouseService = new SummerhouseServiceImpl();
            ISummerhouseService stub = (ISummerhouseService) UnicastRemoteObject.exportObject(summerhouseService, 0);

            // Create or get the registry
            try {
                registry = LocateRegistry.createRegistry(PORT);
                System.out.println("Java RMI registry created.");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(PORT);
                System.out.println("Using existing Java RMI registry.");
            }

            // Bind the service
            registry.rebind("SummerhouseService", stub);
            System.out.println("SummerhouseService bound in registry");
        } catch (Exception e) {
            System.err.println("RMIServer exception:");
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            if (registry != null) {
                registry.unbind("SummerhouseService");
                UnicastRemoteObject.unexportObject(summerhouseService, true);
                UnicastRemoteObject.unexportObject(registry, true);
                System.out.println("Server stopped successfully");
            }
        } catch (Exception e) {
            System.err.println("Error stopping server:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RMIServer server = new RMIServer();
        
        // Add shutdown hook to properly stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down RMI server...");
            server.stop();
        }));

        System.out.println("RMI Server is running...");
        System.out.println("Press Ctrl+C to stop the server.");
    }
} 