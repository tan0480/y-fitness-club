import java.util.Date;

public class Admin extends Staff {

    public Admin() {
        super();
    }

    public Admin(String userID, String userName, String phone, String email,
            String password, String role, Gender gender,
            String staffRole, String shiftSchedule) {
        super(userID, userName, phone, email, password, role, gender, staffRole, shiftSchedule);
    }

    // ================================================================
    // BUSINESS METHODS
    // ================================================================

    public Report generateSystemReport(String reportID, String month, FitnessClub club) {
        int active = 0, expired = 0;
        int targetMonth = Integer.parseInt(month.substring(0, 2)) - 1;
        int targetYear = Integer.parseInt(month.substring(3));
        java.util.Calendar calStart = java.util.Calendar.getInstance();
        java.util.Calendar calEnd = java.util.Calendar.getInstance();

        User[] allUsers = club.getUser();
        for (int i = 0; i < club.getTotalUsers(); i++) {
            if (allUsers[i] instanceof Member) {
                Member m = (Member) allUsers[i];
                if (m.getStartDate() != null && m.getEndDate() != null) {
                    calStart.setTime(m.getStartDate());
                    calEnd.setTime(m.getEndDate());
                    
                    int startM = calStart.get(java.util.Calendar.MONTH);
                    int startY = calStart.get(java.util.Calendar.YEAR);
                    int endM = calEnd.get(java.util.Calendar.MONTH);
                    int endY = calEnd.get(java.util.Calendar.YEAR);

                    boolean startedBeforeOrDuring = (startY < targetYear) || (startY == targetYear && startM <= targetMonth);
                    boolean endedAfterOrDuring = (endY > targetYear) || (endY == targetYear && endM >= targetMonth);

                    if (startedBeforeOrDuring) {
                        if (endedAfterOrDuring) {
                            active++;
                        } else {
                            expired++;
                        }
                    }
                }
            }
        }

        double monthlyRevenue = 0.0;
        Payment[] payments = club.getPayments();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/yyyy");
        for (int i = 0; i < club.getPaymentCount(); i++) {
            if (payments[i].getPaymentDate() != null) {
                if (sdf.format(payments[i].getPaymentDate()).equals(month)) {
                    monthlyRevenue += payments[i].getAmount();
                }
            }
        }

        Report report = new Report(reportID, month, monthlyRevenue, active, expired);
        club.addReport(report);
        club.setLastReport(report);
        System.out.println("Admin " + getUserName() + " generated system report for: " + month);
        return report;
    }

    public void arrangeShiftFor(User user, Shift shift, FitnessClub club) {
        club.addShift(shift, user.getUserID());
        System.out.println("Admin " + getUserName() + " assigned "
                + user.getUserName() + " (" + user.getUserID() + ")"
                + " to Shift [" + shift.getShiftID() + "] "
                + shift.getShiftType() + " " + shift.getStartTime() + "-" + shift.getEndTime());
    }

    public String viewMembershipStatus(Member member) {
        boolean isActive = member.getEndDate() != null && member.getEndDate().after(new Date());
        return String.format("  %-8s %-16s %-10s %-10s %s",
                member.getUserID(),
                member.getUserName(),
                member.getMembershipType(),
                isActive ? "ACTIVE" : "INACTIVE",
                member.getEndDate() != null ? member.getEndDate().toString().substring(0, 10) : "N/A");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        return super.equals(o);
    }
}
