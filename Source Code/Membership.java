import java.util.Date;

public class Membership {
    private String membershipID;
    private String type;
    private Date startDate;
    private Date endDate;
    private MembershipStatus status;

    // No-arg constructor
    public Membership() {
        this.membershipID = "";
        this.type = "";
        this.startDate = null;
        this.endDate = null;
        this.status = null;
    }

    // Parameterized constructor
    public Membership(String membershipID, String type, Date startDate,
                      Date endDate, MembershipStatus status) {
        this.membershipID = membershipID;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void bookSession() {
        if (status != MembershipStatus.ACTIVE) {
            System.out.println("Cannot book session. Membership status: " + status);
            return;
        }
        System.out.println("Session booked under membership: " + membershipID + " (Type: " + type + ").");
    }

    public void cancelSession() {
        System.out.println("Session cancelled under membership: " + membershipID + " (Type: " + type + ").");
    }

    // Getters
    public String getMembershipID()     { return membershipID; }
    public String getType()             { return type; }
    public Date getStartDate()          { return startDate; }
    public Date getEndDate()            { return endDate; }
    public MembershipStatus getStatus() { return status; }

    // Setters
    public void setMembershipID(String membershipID)   { this.membershipID = membershipID; }
    public void setType(String type)                   { this.type = type; }
    public void setStartDate(Date startDate)           { this.startDate = startDate; }
    public void setEndDate(Date endDate)               { this.endDate = endDate; }
    public void setStatus(MembershipStatus status)     { this.status = status; }

    @Override
    public String toString() {
        return "Membership ID: " + membershipID +
               "\nType: " + type +
               "\nStart Date: " + startDate +
               "\nEnd Date: " + endDate +
               "\nStatus: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membership)) return false;
        Membership other = (Membership) o;
        return this.membershipID.equals(other.membershipID);
    }
}
