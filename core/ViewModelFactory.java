package core;

import client.clientViewModel.BookingViewModel;
import client.clientViewModel.PaymentViewModel;
import clientModel.ClientBookingModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private BookingViewModel bookingViewModel;
    private PaymentViewModel paymentViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public BookingViewModel getBookingViewModel() {
        if (bookingViewModel == null) {
            bookingViewModel = new BookingViewModel(modelFactory.getBookingModel());
        }
        return bookingViewModel;
    }

    public PaymentViewModel getPaymentViewModel() {
        if (paymentViewModel == null) {
            paymentViewModel = new PaymentViewModel(modelFactory.getBookingModel());
        }
        return paymentViewModel;
    }
}
