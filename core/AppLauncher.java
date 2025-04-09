package core;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(primaryStage, viewModelFactory);
        viewHandler.start();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
