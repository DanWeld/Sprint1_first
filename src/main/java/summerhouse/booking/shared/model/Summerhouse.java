package summerhouse.booking.shared.model;

import java.time.LocalDate;

public class Summerhouse extends Property {
    private static final long serialVersionUID = 1L;

    public Summerhouse(Long id, String name, String address, int capacity, 
                      double pricePerNight, LocalDate availableFrom, String description) {
        super(id, name, address, capacity, pricePerNight, availableFrom, description);
    }
} 