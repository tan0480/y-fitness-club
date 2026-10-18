public class FitnessClub {
    private String clubName;
    private User[] users;
    private int totalUsers;

    private FitnessClass[] classes;
    private int classCount;

    private Equipment[] equipmentList;
    private int equipmentCount;

    private WeeklySchedule weeklySchedule;

    private Booking[] bookings;
    private String[] bookingOwner;
    private String[] bookingClassId;
    private int bookingCount;

    private Progress[] progresses;
    private String[] progressOwner;
    private int progressCount;

    private Shift[] shifts;
    private String[] shiftAssignee;
    private int shiftCount;

    private Payment[] payments;
    private int paymentCount;
    private Report[] reports;
    private int reportCount;

    private double totalRevenue;
    private Report lastReport;

    public FitnessClub(String clubName) {
        this.clubName = clubName;
        this.users = new User[100];
        this.totalUsers = 0;

        this.classes = new FitnessClass[50];
        this.classCount = 0;

        this.equipmentList = new Equipment[20];
        this.equipmentCount = 0;

        this.weeklySchedule = new WeeklySchedule("SCH-DEFAULT", null, null);

        this.bookings = new Booking[200];
        this.bookingOwner = new String[200];
        this.bookingClassId = new String[200];
        this.bookingCount = 0;

        this.progresses = new Progress[500];
        this.progressOwner = new String[500];
        this.progressCount = 0;

        this.shifts = new Shift[100];
        this.shiftAssignee = new String[100];
        this.shiftCount = 0;

        this.payments = new Payment[500];
        this.paymentCount = 0;

        this.reports = new Report[100];
        this.reportCount = 0;

        this.totalRevenue = 0.0;
        this.lastReport = null;

        initializePredefinedData();
    }

    private void initializePredefinedData() {
        Equipment e1 = new Equipment(1, "Treadmill", "Cardio", 10, EquipStatus.AVAILABLE, null);
        Equipment e2 = new Equipment(2, "Yoga Mat", "Flexibility", 25, EquipStatus.AVAILABLE, null);
        Equipment e3 = new Equipment(3, "Dumbbells", "Strength", 40, EquipStatus.AVAILABLE, null);
        Equipment e4 = new Equipment(4, "Spin Bike", "Cardio", 15, EquipStatus.AVAILABLE, null);
        addEquipment(e1);
        addEquipment(e2);
        addEquipment(e3);
        addEquipment(e4);
    }

    public void addEquipment(Equipment equipment) {
        if (equipmentCount < equipmentList.length) {
            equipmentList[equipmentCount++] = equipment;
        }
    }

    public Equipment[] getEquipmentList() {
        return equipmentList;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public void registerUser(User user) {
        if (totalUsers < users.length) {
            users[totalUsers] = user;
            totalUsers++;
        } else {
            System.out.println("System user capacity reached.");
        }
    }

    public boolean isUniqueID(String uid) {
        for (int i = 0; i < totalUsers; i++) {
            if (users[i].getUserID().equalsIgnoreCase(uid)) {
                return false;
            }
        }
        return true;
    }

    public String getClubName() {
        return clubName;
    }

    public User[] getUser() {
        return users;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public User findUserByID(String uid) {
        for (int i = 0; i < totalUsers; i++) {
            if (users[i].getUserID().equals(uid))
                return users[i];
        }
        return null;
    }

    public Member[] getMembers() {
        int count = 0;
        for (int i = 0; i < totalUsers; i++) {
            if (users[i] instanceof Member)
                count++;
        }
        Member[] members = new Member[count];
        int idx = 0;
        for (int i = 0; i < totalUsers; i++) {
            if (users[i] instanceof Member)
                members[idx++] = (Member) users[i];
        }
        return members;
    }

    public FitnessClass[] getClasses() {
        return classes;
    }

    public int getClassCount() {
        return classCount;
    }

    public void addClassCount() {
        classCount++;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public String[] getBookingOwner() {
        return bookingOwner;
    }

    public String[] getBookingClassId() {
        return bookingClassId;
    }

    public Booking[] getBookings() {
        return bookings;
    }

    public int getBookingCount() {
        return bookingCount;
    }

    public void addBooking(Booking b, String ownerId, String classId) {
        bookings[bookingCount] = b;
        bookingOwner[bookingCount] = ownerId;
        bookingClassId[bookingCount] = classId;
        bookingCount++;
    }

    public int getProgressCount() {
        return progressCount;
    }

    public String[] getProgressOwner() {
        return progressOwner;
    }

    public Progress[] getProgresses() {
        return progresses;
    }

    public void addProgress(Progress p, String ownerId) {
        progresses[progressCount] = p;
        progressOwner[progressCount] = ownerId;
        progressCount++;
    }

    public int getShiftCount() {
        return shiftCount;
    }

    public String[] getShiftAssignee() {
        return shiftAssignee;
    }

    public Shift[] getShifts() {
        return shifts;
    }

    public void addShift(Shift s, String assigneeId) {
        shifts[shiftCount] = s;
        shiftAssignee[shiftCount] = assigneeId;
        shiftCount++;
    }

    public Payment[] getPayments() {
        return payments;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void addPayment(Payment p) {
        if (paymentCount < payments.length) {
            payments[paymentCount++] = p;
        }
    }

    public Report[] getReports() {
        return reports;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void addReport(Report r) {
        if (reportCount < reports.length) {
            reports[reportCount++] = r;
        }
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void addRevenue(double max) {
        totalRevenue += max;
    }

    // Kept for backwards-compatibility or general tracking
    public Report getLastReport() {
        return lastReport;
    }

    public void setLastReport(Report r) {
        lastReport = r;
    }
}
