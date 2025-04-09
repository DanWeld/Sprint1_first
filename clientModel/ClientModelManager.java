package clientModel;

import clientNetwork.Client;

public class ClientModelManager implements ClientBookingModel {
    private Client client;

    public ClientModelManager(Client client) {
        this.client = client;
    }

    @Override
    public boolean bookProperty(String details) {
        return client.bookProperty(details);
    }

    @Override
    public boolean cancelBooking(String details) {
        return client.cancelBooking(details);
    }

    @Override
    public boolean processPayment(String details) {
        return client.processPayment(details);
    }
}
