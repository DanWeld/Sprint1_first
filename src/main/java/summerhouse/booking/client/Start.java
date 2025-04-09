package summerhouse.booking.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import summerhouse.booking.client.view.MainViewController;
import summerhouse.booking.client.viewmodel.MainViewModel;
import summerhouse.booking.shared.network.ISummerhouseService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Connect to RMI server
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ISummerhouseService summerhouseService = (ISummerhouseService) registry.lookup("SummerhouseService");

            // Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent root = loader.load();

            // Initialize view model and controller
            MainViewModel viewModel = new MainViewModel(summerhouseService);
            MainViewController controller = loader.getController();
            controller.init(viewModel);

            // Set up stage
            primaryStage.setTitle("Summerhouse Booking System");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
} 