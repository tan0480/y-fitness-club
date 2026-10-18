import java.util.Date;

public class Payment {
    private String paymentID;
    private double amount;
    private Date paymentDate;
    private String method;

    // No-arg constructor
    public Payment() {
        this.paymentID = "";
        this.amount = 0.0;
        this.paymentDate = null;
        this.method = "";
    }

    // Parameterized constructor
    public Payment(String paymentID, double amount, Date paymentDate, String method) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.method = method;
    }

    // Process the payment and record the date
    public void makePayment() {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid payment amount: RM" + amount + ". Amount must be greater than 0.");
            }
            this.paymentDate = new Date();
            System.out.println("Payment successful!");
            System.out.println("  Payment ID : " + paymentID);
            System.out.println("  Amount     : RM" + String.format("%.2f", amount));
            System.out.println("  Method     : " + method);
            System.out.println("  Date       : " + paymentDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during payment: " + e.getMessage());
        }
    }

    // Getters
    public String getPaymentID()  { return paymentID; }
    public double getAmount()     { return amount; }
    public Date getPaymentDate()  { return paymentDate; }
    public String getMethod()     { return method; }

    // Setters
    public void setPaymentID(String paymentID)     { this.paymentID = paymentID; }
    public void setAmount(double amount)           { this.amount = amount; }
    public void setPaymentDate(Date paymentDate)   { this.paymentDate = paymentDate; }
    public void setMethod(String method)           { this.method = method; }

    @Override
    public String toString() {
        return "Payment ID: " + paymentID +
               "\nAmount: RM" + String.format("%.2f", amount) +
               "\nPayment Date: " + paymentDate +
               "\nMethod: " + method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment other = (Payment) o;
        return this.paymentID.equals(other.paymentID);
    }
}
