module summerhouse.booking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires java.management.rmi;

    opens summerhouse.booking.client to javafx.fxml;
    opens summerhouse.booking.client.view to javafx.fxml;
    opens summerhouse.booking.client.viewmodel to javafx.fxml;
    opens summerhouse.booking.shared.model;
    opens summerhouse.booking.shared.network;
    opens summerhouse.booking.server;

    exports summerhouse.booking.client;
    exports summerhouse.booking.client.view;
    exports summerhouse.booking.client.viewmodel;
    exports summerhouse.booking.shared.model;
    exports summerhouse.booking.shared.network;
    exports summerhouse.booking.server;
} 