import java.util.Date;

public class Member extends User {
    private MembershipType membershipType;
    private Date startDate;
    private Date endDate;
    private double weight; // in kg
    private double height; // in metres

    public Member() {
        super();
        this.membershipType = null;
        this.startDate = null;
        this.endDate = null;
        this.weight = 0.0;
        this.height = 0.0;
    }

    public Member(String userID, String userName, String phone, String email,
                  String password, String role, Gender gender,
                  MembershipType membershipType, Date startDate, Date endDate,
                  double weight, double height) {
        super(userID, userName, phone, email, password, role, gender);
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weight = weight;
        this.height = height;
    }

    // ================================================================
    // BUSINESS METHODS
    // ================================================================

    public void makePayment(double amount, MembershipType newType, Payment payment, FitnessClub club) {
        if (payment.getPaymentDate() != null) {
            club.addRevenue(amount);
            club.addPayment(payment);
            this.setMembershipType(newType);
            this.renewMembership();
            System.out.println("\n  Payment record:");
            System.out.println(payment);
        }
    }

    public boolean bookClass(FitnessClass fitnessClass, FitnessClub club) {
        if (endDate != null && endDate.after(new Date())) {
            if (fitnessClass.enroll(this)) {
                int bookingID = 1001 + club.getBookingCount();
                Booking booking = new Booking(bookingID, new Date(), BookingStatus.PENDING);
                booking.confirm();
                
                club.addBooking(booking, this.getUserID(), String.valueOf(fitnessClass.getClassId()));
                System.out.println("\n  Booking confirmed!");
                System.out.println(booking);
                System.out.println("\n  Updated class:");
                System.out.println(fitnessClass);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Booking failed. " + getUserName() + "'s membership has expired or is not active.");
            return false;
        }
    }

    public void recordProgress(double newWeight, String notes, FitnessClub club) {
        String pID = "PRG-" + getUserID() + "-" + System.currentTimeMillis();
        Progress newEntry = new Progress(pID, new Date(), newWeight, notes);
        this.setWeight(newWeight);

        club.addProgress(newEntry, this.getUserID());
        System.out.println("\n  Progress recorded!");
        System.out.println(newEntry);
    }

    public void renewMembership() {
        if (endDate != null && endDate.after(new Date())) {
            long millis = endDate.getTime() + (30L * 24 * 60 * 60 * 1000);
            this.endDate = new Date(millis);
            System.out.println(getUserName() + "'s membership has been extended. New end date: " + endDate);
        } else {
            this.startDate = new Date();
            long millis = startDate.getTime() + (30L * 24 * 60 * 60 * 1000);
            this.endDate = new Date(millis);
            System.out.println(getUserName() + "'s membership has been renewed. Start: " + startDate + " | End: " + endDate);
        }
    }

    public double calculateBMI() {
        if (height <= 0) {
            System.out.println("Cannot calculate BMI. Height is not set.");
            return 0.0;
        }
        double bmi = weight / (height * height);
        System.out.println(getUserName() + "'s BMI: " + String.format("%.2f", bmi));

        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25.0) {
            System.out.println("Category: Normal weight");
        } else if (bmi < 30.0) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obese");
        }
        return bmi;
    }

    // Getters and Setters
    public MembershipType getMembershipType() { return membershipType; }
    public Date getStartDate()                { return startDate; }
    public Date getEndDate()                  { return endDate; }
    public double getWeight()                 { return weight; }
    public double getHeight()                 { return height; }

    public void setMembershipType(MembershipType membershipType) { this.membershipType = membershipType; }
    public void setStartDate(Date startDate)                     { this.startDate = startDate; }
    public void setEndDate(Date endDate)                         { this.endDate = endDate; }
    public void setWeight(double weight)                         { this.weight = weight; }
    public void setHeight(double height)                         { this.height = height; }

    @Override
    public String toString() {
        return super.toString() +
               "\nMembership Type: " + membershipType +
               "\nStart Date: " + startDate +
               "\nEnd Date: " + endDate +
               "\nWeight: " + weight + " kg" +
               "\nHeight: " + height + " m";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        return super.equals(o);
    }
}
