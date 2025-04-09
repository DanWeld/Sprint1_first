package core;

import clientModel.ClientBookingModel;
import clientModel.ClientModelManager;
import clientNetwork.Client;
import clientNetwork.SocketClient;

public class ModelFactory {
    private ClientFactory clientFactory;
    private ClientBookingModel bookingModel;

    public ModelFactory() {
        this.clientFactory = new ClientFactory();
    }

    public ClientBookingModel getBookingModel() {
        if (bookingModel == null) {
            bookingModel = new ClientModelManager(clientFactory.getClient());
        }
        return bookingModel;
    }
}
