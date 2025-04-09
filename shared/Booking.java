package shared;

import java.util.Date;

public class Booking {
    private String property;
    private String user;
    private Date startDate;
    private Date endDate;

    public Booking(String property, String user, Date startDate, Date endDate) {
        this.property = property;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProperty() {
        return property;
    }

    public String getUser() {
        return user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
