package clientView;

import core.ViewHandler;
import core.ViewModelFactory;
import client.clientViewModel.PaymentViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PaymentController implements ViewController {
    @FXML
    private TextField paymentDetails;

    private ViewHandler viewHandler;
    private PaymentViewModel paymentViewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.paymentViewModel = viewModelFactory.getPaymentViewModel();
    }

    @FXML
    public void onPayButton() {
        paymentViewModel.processPayment();
    }

    @FXML
    public void onCancelButton() {
        // Logic to handle cancel button
    }
}
