public class Report {
    private String reportID;
    private String month;
    private double totalRevenue;
    private int activeMembers;
    private int expiredMembers;

    // No-arg constructor
    public Report() {
        this.reportID = "";
        this.month = "";
        this.totalRevenue = 0.0;
        this.activeMembers = 0;
        this.expiredMembers = 0;
    }

    // Parameterized constructor
    public Report(String reportID, String month, double totalRevenue,
                  int activeMembers, int expiredMembers) {
        this.reportID = reportID;
        this.month = month;
        this.totalRevenue = totalRevenue;
        this.activeMembers = activeMembers;
        this.expiredMembers = expiredMembers;
    }

    // Print the formatted monthly report
    public void generateReport() {
        int totalMembers = activeMembers + expiredMembers;
        double retentionRate = totalMembers > 0
                ? ((double) activeMembers / totalMembers) * 100 : 0;

        System.out.println("==========================================");
        System.out.println("       MONTHLY FITNESS CLUB REPORT        ");
        System.out.println("==========================================");
        System.out.println("  Report ID       : " + reportID);
        System.out.println("  Month           : " + month);
        System.out.println("  Total Revenue   : $" + String.format("%.2f", totalRevenue));
        System.out.println("  Active Members  : " + activeMembers);
        System.out.println("  Expired Members : " + expiredMembers);
        System.out.println("  Total Members   : " + totalMembers);
        System.out.println("  Retention Rate  : " + String.format("%.1f", retentionRate) + "%");
        System.out.println("==========================================");
    }

    // Getters
    public String getReportID()      { return reportID; }
    public String getMonth()         { return month; }
    public double getTotalRevenue()  { return totalRevenue; }
    public int getActiveMembers()    { return activeMembers; }
    public int getExpiredMembers()   { return expiredMembers; }

    // Setters
    public void setReportID(String reportID)              { this.reportID = reportID; }
    public void setMonth(String month)                    { this.month = month; }
    public void setTotalRevenue(double totalRevenue)      { this.totalRevenue = totalRevenue; }
    public void setActiveMembers(int activeMembers)       { this.activeMembers = activeMembers; }
    public void setExpiredMembers(int expiredMembers)     { this.expiredMembers = expiredMembers; }

    @Override
    public String toString() {
        return "Report ID: " + reportID +
               "\nMonth: " + month +
               "\nTotal Revenue: RM" + String.format("%.2f", totalRevenue) +
               "\nActive Members: " + activeMembers +
               "\nExpired Members: " + expiredMembers +
               "\nTotal Members: " + (activeMembers + expiredMembers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report other = (Report) o;
        return this.reportID.equals(other.reportID);
    }
}
