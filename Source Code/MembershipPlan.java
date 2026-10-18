public class MembershipPlan extends Membership {
    private int planId;
    private String name;
    private double price;
    private int durationDays;
    private String privileges;

    // No-arg constructor
    public MembershipPlan() {
        super();
        this.planId = 0;
        this.name = "";
        this.price = 0.0;
        this.durationDays = 0;
        this.privileges = "";
    }

    // Parameterized constructor
    public MembershipPlan(int planId, String name, double price,
                          int durationDays, String privileges) {
        super("MP-" + planId, name, null, null, MembershipStatus.ACTIVE);
        this.planId = planId;
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
        this.privileges = privileges;
    }

    public String getDetails() {
        return "MembershipPlan [Plan ID: " + planId
                + " | Name: " + name
                + " | Price: $" + String.format("%.2f", price)
                + " | Duration: " + durationDays + " days"
                + " | Privileges: " + privileges + "]";
    }

    public void updatePrice() {
        System.out.println("Price for plan '" + name + "' updated to $" + String.format("%.2f", price));
    }

    // Getters
    public int getPlanId()        { return planId; }
    public String getName()       { return name; }
    public double getPrice()      { return price; }
    public int getDurationDays()  { return durationDays; }
    public String getPrivileges() { return privileges; }

    // Setters
    public void setPlanId(int planId)              { this.planId = planId; }
    public void setName(String name)               { this.name = name; }
    public void setPrice(double price)             { this.price = price; }
    public void setDurationDays(int durationDays)  { this.durationDays = durationDays; }
    public void setPrivileges(String privileges)   { this.privileges = privileges; }

    @Override
    public String toString() {
        return super.toString() +
               "\nPlan ID: " + planId +
               "\nPlan Name: " + name +
               "\nPrice: RM" + String.format("%.2f", price) +
               "\nDuration: " + durationDays + " days" +
               "\nPrivileges: " + privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembershipPlan)) return false;
        MembershipPlan other = (MembershipPlan) o;
        return this.planId == other.planId && super.equals(o);
    }
}
