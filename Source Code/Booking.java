import java.util.Date;

public class Booking {
    private int bookingID;
    private Date bookingDate;
    private BookingStatus status;

    // No-arg constructor
    public Booking() {
        this.bookingID = 0;
        this.bookingDate = null;
        this.status = BookingStatus.PENDING;
    }

    // Parameterized constructor
    public Booking(int bookingID, Date bookingDate, BookingStatus status) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public void confirm() {
        this.status = BookingStatus.CONFIRMED;
        System.out.println("Booking " + bookingID + " has been confirmed.");
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        System.out.println("Booking " + bookingID + " has been cancelled.");
    }

    public String getDetails() {
        return "Booking [ID: " + bookingID
                + " | Date: " + bookingDate
                + " | Status: " + status + "]";
    }

    public void reschedule() {
        this.bookingDate = new Date();
        this.status = BookingStatus.PENDING;
        System.out.println("Booking " + bookingID + " rescheduled to " + bookingDate + ". Status: Pending.");
    }

    // Check if the booking date is available (not in the past)
    public boolean checkAvailability() {
        if (bookingDate != null && bookingDate.after(new Date())) {
            System.out.println("Booking date " + bookingDate + " is available.");
            return true;
        }
        System.out.println("Booking date is not available.");
        return false;
    }

    // Getters
    public int getBookingID()        { return bookingID; }
    public Date getBookingDate()     { return bookingDate; }
    public BookingStatus getStatus() { return status; }

    // Setters
    public void setBookingID(int bookingID)           { this.bookingID = bookingID; }
    public void setBookingDate(Date bookingDate)      { this.bookingDate = bookingDate; }
    public void setStatus(BookingStatus status)       { this.status = status; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingID +
               "\nBooking Date: " + bookingDate +
               "\nStatus: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking other = (Booking) o;
        return this.bookingID == other.bookingID;
    }
}
