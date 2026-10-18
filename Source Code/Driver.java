import java.util.Date;
import java.util.Scanner;

public class Driver {

    static Scanner sc = new Scanner(System.in);
    static FitnessClub club = new FitnessClub("Y Fitness Club");

    public static void main(String[] args) {
        // Preset accounts
        Staff presetStaff = new Staff(
                "S001", "Mary", "012-3456789", "mary@yfitnessclub.com",
                "staff123", "Staff", Gender.FEMALE, "Front Desk", "");

        Admin presetAdmin = new Admin(
                "A001", "AdminLee", "012-0000001", "admin@yfitnessclub.com",
                "admin123", "Admin", Gender.FEMALE, "Administration", "");

        club.registerUser(presetStaff);
        club.registerUser(presetAdmin);
        
        // preset member and coach 
        
        // Member m1 = new Member("1", "Alice", "012-1111111", "alice@test.com", "123", "Member", Gender.FEMALE,
        //         MembershipType.BASIC, new Date(), new Date(System.currentTimeMillis() + 86400000L * 30), 55.0, 1.6);
        // Member m2 = new Member("2", "Bob", "012-2222222", "bob@test.com", "123", "Member", Gender.MALE,
        //         MembershipType.PREMIUM, new Date(), new Date(System.currentTimeMillis() + 86400000L * 30), 75.0, 1.8);
        // Member m3 = new Member("3", "Charlie", "012-3333333", "charlie@test.com", "123", "Member", Gender.MALE,
        //         MembershipType.VIP, new Date(), new Date(System.currentTimeMillis() + 86400000L * 30), 80.0, 1.75);

        // Coach c1 = new Coach("c", "Dave", "012-4444444", "dave@test.com", "123", "Coach", Gender.MALE, "Strength",
        //         new Member[0]);

        // club.registerUser(m1);
        // club.registerUser(m2);
        // club.registerUser(m3);
        // club.registerUser(c1);

        printBanner();

        boolean running = true;
        while (running) {
            printHeader("MAIN MENU  -  " + club.getClubName());
            System.out.println("  1. Register User");
            System.out.println("  2. Staff Login");
            System.out.println("  3. Admin Login");
            System.out.println("  4. Coach Login");
            System.out.println("  5. Member Login");
            System.out.println("  0. Exit");
            int choice = getIntInputRange("\n  Your choice (0-5): ", 0, 5);
            switch (choice) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    staffLogin();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    coachLogin();
                    break;
                case 5:
                    memberLogin();
                    break;
                case 0:
                    System.out.println("\n  Thank you for using " + club.getClubName() + ". Goodbye!\n");
                    running = false;
                    break;
            }
        }
    }

    // ================================================================
    // REGISTRATION
    // ================================================================
    static void registerMenu() {
        printHeader("REGISTER");
        System.out.println("  1. Register as Member");
        System.out.println("  2. Register as Coach");
        System.out.println("  0. Back");
        int option = getIntInputRange("  Your choice (0-2): ", 0, 2);
        switch (option) {
            case 1:
                registerMember();
                break;
            case 2:
                registerCoach();
                break;
        }
    }

    static void registerMember() {
        printHeader("MEMBER REGISTRATION");
        System.out.print("  User ID       : ");
        String uid = sc.nextLine().trim();
        if (!club.isUniqueID(uid)) {
            System.out.println("  Error: User ID '" + uid + "' is already taken.");
            return;
        }
        System.out.print("  Full Name     : ");
        String name = sc.nextLine().trim();
        System.out.print("  Phone         : ");
        String phone = sc.nextLine().trim();

        String email;
        while (true) {
            System.out.print("  Email         : ");
            email = sc.nextLine().trim();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                break;
            System.out.println("  Invalid email format. Please try again.");
        }

        System.out.print("  Password      : ");
        String pwd = sc.nextLine().trim();

        System.out.println("  Gender: 1=Male  2=Female  3=Other");
        int gc = getIntInputRange("  Select (1-3)  : ", 1, 3);
        Gender gender = gc == 2 ? Gender.FEMALE : gc == 3 ? Gender.OTHER : Gender.MALE;

        System.out.println("\n  Membership Type:");
        System.out.println("    1. BASIC    (RM  99.90/mo)");
        System.out.println("    2. PREMIUM  (RM 199.90/mo)");
        System.out.println("    3. VIP      (RM 299.90/mo)");
        int mc = getIntInputRange("  Select (1-3)  : ", 1, 3);
        MembershipType mType = mc == 2 ? MembershipType.PREMIUM : mc == 3 ? MembershipType.VIP : MembershipType.BASIC;
        double amount = (mc == 2) ? 199.90 : (mc == 3) ? 299.90 : 99.90;

        System.out.println("\n  Payment Methods:");
        System.out.println("  1. Credit Card    2. Online Banking    3. Cash");
        int methodC = getIntInputRange("  Select method (1-3): ", 1, 3);
        String method = (methodC == 1) ? "Credit Card" : (methodC == 2) ? "Online Banking" : "Cash";

        double weight = getDoubleInput("  Weight (kg)   : ");
        double height = getDoubleInput("  Height (m)    : ");

        Member newMember = new Member(uid, name, phone, email, pwd,
                "Member", gender, mType, null, null, weight, height);

        String payID = "PAY-" + uid + "-" + System.currentTimeMillis();
        Payment payment = new Payment(payID, amount, null, method);
        payment.makePayment();
        newMember.makePayment(amount, mType, payment, club);

        club.registerUser(newMember);
        System.out.println("\n  Registration successful! Log in with User ID: " + uid);
    }

    static void registerCoach() {
        printHeader("COACH REGISTRATION");
        System.out.print("  User ID       : ");
        String uid = sc.nextLine().trim();
        if (!club.isUniqueID(uid)) {
            System.out.println("  Error: User ID '" + uid + "' is already taken.");
            return;
        }
        System.out.print("  Full Name     : ");
        String name = sc.nextLine().trim();
        System.out.print("  Phone         : ");
        String phone = sc.nextLine().trim();

        String email;
        while (true) {
            System.out.print("  Email         : ");
            email = sc.nextLine().trim();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                break;
            System.out.println("  Invalid email format. Please try again.");
        }

        System.out.print("  Password      : ");
        String pwd = sc.nextLine().trim();

        System.out.println("  Gender: 1=Male  2=Female  3=Other");
        int gc = getIntInputRange("  Select (1-3)  : ", 1, 3);
        Gender gender = gc == 2 ? Gender.FEMALE : gc == 3 ? Gender.OTHER : Gender.MALE;

        System.out.print("  Specialization: ");
        String spec = sc.nextLine().trim();

        Coach newCoach = new Coach(uid, name, phone, email, pwd,
                "Coach", gender, spec, new Member[0]);
        club.registerUser(newCoach);
        System.out.println("\n  Registration successful! Log in with User ID: " + uid);
    }

    // ================================================================
    // LOGINS
    // ================================================================
    static void staffLogin() {
        printHeader("STAFF LOGIN");
        System.out.print("  User ID  : ");
        String uid = sc.nextLine().trim();
        System.out.print("  Password : ");
        String pwd = sc.nextLine().trim();
        User u = club.findUserByID(uid);
        if (u != null && u.getClass() == Staff.class && pwd.equals(u.getPassword())) {
            System.out.println("\n  Welcome, " + u.getUserName() + "!");
            staffMenu((Staff) u);
        } else {
            System.out.println("  Login failed. Invalid User ID or password.");
        }
    }

    static void adminLogin() {
        printHeader("ADMIN LOGIN");
        System.out.print("  User ID  : ");
        String uid = sc.nextLine().trim();
        System.out.print("  Password : ");
        String pwd = sc.nextLine().trim();
        User u = club.findUserByID(uid);
        if (u instanceof Admin && pwd.equals(u.getPassword())) {
            System.out.println("\n  Welcome, " + u.getUserName() + "!");
            adminMenu((Admin) u);
        } else {
            System.out.println("  Login failed. Invalid User ID or password.");
        }
    }

    static void coachLogin() {
        printHeader("COACH LOGIN");
        System.out.print("  User ID  : ");
        String uid = sc.nextLine().trim();
        System.out.print("  Password : ");
        String pwd = sc.nextLine().trim();
        User u = club.findUserByID(uid);
        if (u instanceof Coach && pwd.equals(u.getPassword())) {
            System.out.println("\n  Welcome, " + u.getUserName() + "!");
            coachMenu((Coach) u);
        } else {
            System.out.println("  Login failed. Invalid User ID or password.");
        }
    }

    static void memberLogin() {
        printHeader("MEMBER LOGIN");
        System.out.print("  User ID  : ");
        String uid = sc.nextLine().trim();
        System.out.print("  Password : ");
        String pwd = sc.nextLine().trim();
        User u = club.findUserByID(uid);
        if (u instanceof Member && pwd.equals(u.getPassword())) {
            System.out.println("\n  Welcome, " + u.getUserName() + "!");
            memberMenu((Member) u);
        } else {
            System.out.println("  Login failed. Invalid User ID or password.");
        }
    }

    // ================================================================
    // MODEL UI: STAFF
    // ================================================================
    static void staffMenu(Staff staff) {
        boolean inMenu = true;
        while (inMenu) {
            printHeader("STAFF MENU  -  " + staff.getUserName() + "  [" + staff.getUserID() + "]");
            System.out.println("  1. View Equipment Status");
            System.out.println("  2. Change Equipment Status");
            System.out.println("  3. Arrange My Shift");
            System.out.println("  0. Logout");
            int choice = getIntInputRange("\n  Your choice (0-3): ", 0, 3);
            switch (choice) {
                case 1:
                    viewEquipmentStatus();
                    break;
                case 2:
                    changeEquipmentStatus(staff);
                    break;
                case 3:
                    staffArrangeShift(staff);
                    break;
                case 0:
                    System.out.println("\n  Logged out. Goodbye, " + staff.getUserName() + "!");
                    inMenu = false;
                    break;
            }
        }
    }

    static void viewEquipmentStatus() {
        printHeader("EQUIPMENT STATUS");
        System.out.println(String.format("  %-6s  %-16s  %-14s  %-6s  %s", "ID", "Name", "Category", "Qty", "Status"));
        System.out.println("  " + repeat('-', 65));
        Equipment[] eqList = club.getEquipmentList();
        for (int i = 0; i < club.getEquipmentCount(); i++) {
            Equipment eq = eqList[i];
            System.out.println(String.format("  %-6d  %-16s  %-14s  %-6s  %s",
                    eq.getEquipmentId(), eq.getName(), eq.getCategory(),
                    eq.getAvailableQuantity() + "/" + eq.getTotalQuantity(), eq.getStatus()));
        }
    }

    static void changeEquipmentStatus(Staff staff) {
        printHeader("CHANGE EQUIPMENT STATUS");
        viewEquipmentStatus();
        Equipment target = null;
        while (true) {
            int eqID = getIntInput("\n  Enter Equipment ID (0 to cancel): ");
            if (eqID == 0)
                return;

            Equipment[] eqList = club.getEquipmentList();
            for (int i = 0; i < club.getEquipmentCount(); i++) {
                if (eqList[i].getEquipmentId() == eqID) {
                    target = eqList[i];
                    break;
                }
            }
            if (target != null)
                break;
            System.out.println("  Error: Equipment ID not found. Please try again.");
        }

        EquipStatus[] statuses = EquipStatus.values();
        System.out.println("\n  Select new status:");
        for (int i = 0; i < statuses.length; i++) {
            System.out.println("    " + (i + 1) + ". " + statuses[i]);
        }
        int si = getIntInputRange("  Select (1-" + statuses.length + "): ", 1, statuses.length) - 1;
        staff.changeEquipmentStatus(target, statuses[si]);
    }

    static void staffArrangeShift(Staff staff) {
        printHeader("ARRANGE MY SHIFT");
        System.out.print("  Shift ID          : ");
        String sid = sc.nextLine().trim();

        String st, et;
        while (true) {
            System.out.print("  Start Time (HH:MM): ");
            st = sc.nextLine().trim();
            if (st.matches("^([01]\\d|2[0-3]):[0-5]\\d$"))
                break;
            System.out.println("  Invalid time format. Use HH:MM (00:00-23:59).");
        }
        while (true) {
            System.out.print("  End Time   (HH:MM): ");
            et = sc.nextLine().trim();
            if (et.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                if (et.compareTo(st) > 0)
                    break;
                else
                    System.out.println("  [Error] End Time must be strictly later than Start Time (" + st + ").");
            } else {
                System.out.println("  Invalid time format. Use HH:MM (00:00-23:59).");
            }
        }

        System.out.println("  Types: 1. Morning  2. Afternoon  3. Evening");
        int typeI = getIntInputRange("  Shift Type (1-3): ", 1, 3);
        String type = (typeI == 1) ? "Morning" : (typeI == 2) ? "Afternoon" : "Evening";

        Shift shift = new Shift(sid, new Date(), st, et, type);
        staff.arrangeShift(shift, club);
    }

    // ================================================================
    // MODEL UI: ADMIN
    // ================================================================
    static void adminMenu(Admin admin) {
        boolean inMenu = true;
        while (inMenu) {
            printHeader("ADMIN MENU  -  " + admin.getUserName() + "  [" + admin.getUserID() + "]");
            System.out.println("  1. View Equipment Status");
            System.out.println("  2. Change Equipment Status");
            System.out.println("  3. Generate System Report");
            System.out.println("  4. View Report History");
            System.out.println("  5. View Member Membership Status");
            System.out.println("  6. Arrange Shift for Staff / Coach");
            System.out.println("  0. Logout");
            int choice = getIntInputRange("\n  Your choice (0-6): ", 0, 6);
            switch (choice) {
                case 1:
                    viewEquipmentStatus();
                    break;
                case 2:
                    changeEquipmentStatus(admin);
                    break;
                case 3:
                    adminGenerateReport(admin);
                    break;
                case 4:
                    viewReportHistory();
                    break;
                case 5:
                    adminViewMembership(admin);
                    break;
                case 6:
                    adminArrangeShift(admin);
                    break;
                case 0:
                    System.out.println("  Logged out.");
                    inMenu = false;
                    break;
            }
        }
    }

    static void adminGenerateReport(Admin admin) {
        printHeader("GENERATE SYSTEM REPORT");
        String month;
        while (true) {
            System.out.print("  Month/Year (MM/YYYY) : ");
            month = sc.nextLine().trim();
            if (month.matches("^(0[1-9]|1[0-2])/\\d{4}$"))
                break;
            System.out.println("  Invalid format. Please use exactly MM/YYYY (e.g. 04/2026).");
        }
        String rID = "RPT-" + System.currentTimeMillis();
        Report r = admin.generateSystemReport(rID, month, club);
        System.out.println("\n  Report generated successfully! Details directly below:\n");
        System.out.println(r);
    }

    static void viewReportHistory() {
        printHeader("REPORT HISTORY");
        int count = club.getReportCount();
        if (count == 0) {
            System.out.println("  No reports have been generated yet.");
            return;
        }

        System.out.println(String.format("  %-4s  %-12s  %-10s", "No.", "Month", "Report ID"));
        System.out.println("  " + repeat('-', 35));
        Report[] reports = club.getReports();
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("  %-4d  %-12s  %-10s",
                    i + 1, reports[i].getMonth(), reports[i].getReportID()));
        }

        int sel = getIntInputRange("\n  Select report to view (0 to cancel): ", 0, count);
        if (sel == 0)
            return;

        System.out.println("\n" + reports[sel - 1]);
    }

    static void adminViewMembership(Admin admin) {
        printHeader("MEMBER MEMBERSHIP STATUS");
        User[] allUsers = club.getUser();
        boolean found = false;
        System.out
                .println(String.format("  %-8s  %-16s  %-10s  %-10s  %s", "ID", "Name", "Type", "Status", "End Date"));
        System.out.println("  " + repeat('-', 65));
        for (int i = 0; i < club.getTotalUsers(); i++) {
            if (allUsers[i] instanceof Member) {
                System.out.println(admin.viewMembershipStatus((Member) allUsers[i]));
                found = true;
            }
        }
        if (!found)
            System.out.println("  No members registered yet.");
    }

    static void adminArrangeShift(Admin admin) {
        printHeader("ARRANGE SHIFT FOR STAFF / COACH");
        User[] allUsers = club.getUser();
        int staffCount = 0;
        int[] indices = new int[club.getTotalUsers()];

        System.out.println(String.format("  %-4s  %-16s  %-8s  %s", "No.", "Name", "ID", "Role"));
        System.out.println("  " + repeat('-', 45));
        for (int i = 0; i < club.getTotalUsers(); i++) {
            User u = allUsers[i];
            if (u instanceof Staff || u instanceof Coach) {
                indices[staffCount] = i;
                staffCount++;
                System.out.println(String.format("  %-4d  %-16s  %-8s  %s",
                        staffCount, u.getUserName(), u.getUserID(), u.getRole()));
            }
        }
        if (staffCount == 0) {
            System.out.println("  No staff or coaches registered.");
            return;
        }

        int sel = getIntInputRange("\n  Select user (1-" + staffCount + "): ", 1, staffCount) - 1;
        User selectedUser = allUsers[indices[sel]];

        System.out.print("  Shift ID          : ");
        String sid = sc.nextLine().trim();

        String st, et;
        while (true) {
            System.out.print("  Start Time (HH:MM): ");
            st = sc.nextLine().trim();
            if (st.matches("^([01]\\d|2[0-3]):[0-5]\\d$"))
                break;
            System.out.println("  Invalid time format. Use HH:MM (00:00-23:59).");
        }
        while (true) {
            System.out.print("  End Time   (HH:MM): ");
            et = sc.nextLine().trim();
            if (et.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                if (et.compareTo(st) > 0)
                    break;
                else
                    System.out.println("  [Error] End Time must be strictly later than Start Time (" + st + ").");
            } else {
                System.out.println("  Invalid time format. Use HH:MM (00:00-23:59).");
            }
        }

        System.out.println("  Types: 1. Morning  2. Afternoon  3. Evening");
        int typeI = getIntInputRange("  Shift Type (1-3): ", 1, 3);
        String type = (typeI == 1) ? "Morning" : (typeI == 2) ? "Afternoon" : "Evening";

        Shift shift = new Shift(sid, new Date(), st, et, type);
        admin.arrangeShiftFor(selectedUser, shift, club);
    }

    // ================================================================
    // MODEL UI: COACH
    // ================================================================
    static void coachMenu(Coach coach) {
        boolean inMenu = true;
        while (inMenu) {
            printHeader("COACH MENU  -  " + coach.getUserName() + "  [" + coach.getUserID() + "]");
            System.out.println("  1. Schedule Fitness Class");
            System.out.println("  2. View Weekly Schedule");
            System.out.println("  3. Manage Class Members");
            System.out.println("  4. View Member Progress");
            System.out.println("  0. Logout");
            int choice = getIntInputRange("\n  Your choice (0-4): ", 0, 4);
            switch (choice) {
                case 1:
                    coachScheduleClass(coach);
                    break;
                case 2:
                    coachViewSchedule();
                    break;
                case 3:
                    coachManageMembers(coach);
                    break;
                case 4:
                    coachViewProgress();
                    break;
                case 0:
                    System.out.println("  Logged out.");
                    inMenu = false;
                    break;
            }
        }
    }

    static void coachScheduleClass(Coach coach) {
        printHeader("SCHEDULE FITNESS CLASS");
        int classId = getIntInput("  Class ID       : ");

        FitnessClass[] existingClasses = club.getClasses();
        for (int i = 0; i < club.getClassCount(); i++) {
            if (existingClasses[i].getClassId() == classId) {
                System.out.println("  Error: Class ID " + classId + " already exists. Returning to menu.");
                return;
            }
        }

        System.out.print("  Class Name     : ");
        String name = sc.nextLine().trim();

        String date;
        while (true) {
            System.out.print("  Date (YYYY-MM-DD): ");
            date = sc.nextLine().trim();
            if (date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$"))
                break;
            System.out.println("  Invalid format. Please use YYYY-MM-DD.");
        }

        String time;
        while (true) {
            System.out.print("  Time (HH:MM)     : ");
            time = sc.nextLine().trim();
            if (time.matches("^([01]\\d|2[0-3]):[0-5]\\d$"))
                break;
            System.out.println("  Invalid format. Please use HH:MM.");
        }

        ClassType[] types = ClassType.values();
        System.out.println("  Class Types:");
        for (int i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ". " + types[i]);
        }
        int ti = getIntInputRange("  Select type (1-" + types.length + "): ", 1, types.length) - 1;

        int cap = getIntInput("  Capacity       : ");
        int dur = getIntInput("  Duration (mins): ");
        System.out.print("  Location       : ");
        String loc = sc.nextLine().trim();

        System.out.println("\n  Equipment Requirements:");
        System.out.println(String.format("  %-5s  %-16s  %-6s  %s", "No.", "Name", "Qty", "Status"));
        System.out.println("  " + repeat('-', 44));
        Equipment[] el = club.getEquipmentList();
        for (int i = 0; i < club.getEquipmentCount(); i++) {
            System.out.println(String.format("  %-5d  %-16s  %-6s  %s",
                    i + 1, el[i].getName(), el[i].getAvailableQuantity() + "/" + el[i].getTotalQuantity(),
                    el[i].getStatus()));
        }

        Equipment assignedEq = null;
        int reqQty = 0;

        while (true) {
            int eqi = getIntInputRange("  Assign type (0-" + club.getEquipmentCount() + " | 0=none): ", 0, club.getEquipmentCount());
            if (eqi == 0)
                break;

            assignedEq = el[eqi - 1];
            if (assignedEq.getStatus() == EquipStatus.MAINTENANCE || assignedEq.getStatus() == EquipStatus.BROKEN) {
                System.out.println("  Error: Selected equipment requires maintenance.");
                assignedEq = null;
                continue;
            }

            reqQty = getIntInput("  Quantity needed: ");
            if (reqQty > assignedEq.getAvailableQuantity()) {
                System.out.println("  Error: Insufficient quantity available. Only "
                        + assignedEq.getAvailableQuantity() + " left. Try a smaller amount or another item.");
                assignedEq = null;
                continue;
            }
            break;
        }

        coach.assignFitnessClass(classId, name, types[ti], date, time, cap, dur, loc, club, assignedEq, reqQty);
    }

    static void coachViewSchedule() {
        printHeader("WEEKLY SCHEDULE");
        System.out.println(club.getWeeklySchedule());
    }

    static void coachManageMembers(Coach coach) {
        printHeader("MANAGE CLASS MEMBERS");
        if (club.getClassCount() == 0) {
            System.out.println("  No classes scheduled yet.");
            return;
        }

        System.out.println("  Classes:");
        System.out.println(String.format("  %-4s  %-22s  %-12s  %s", "No.", "Class Name", "Type", "Enrollment"));
        System.out.println("  " + repeat('-', 58));
        FitnessClass[] classes = club.getClasses();
        for (int i = 0; i < club.getClassCount(); i++) {
            System.out.println(String.format("  %-4d  %-22s  %-12s  %d/%d",
                    i + 1, classes[i].getName(), classes[i].getType(),
                    classes[i].getEnrolledCount(), classes[i].getCapacity()));
        }
        int ci = getIntInputRange("  Select class (1-" + club.getClassCount() + "): ", 1, club.getClassCount()) - 1;
        FitnessClass selected = classes[ci];

        System.out.println("\n  Actions:");
        System.out.println("  1. Assign member to class");
        System.out.println("  2. Remove member from class");
        System.out.println("  3. View enrolled members");
        int action = getIntInputRange("  Select action (1-3): ", 1, 3);

        if (action == 3) {
            System.out.println();
            selected.viewMembers();
            return;
        }

        if (action == 2) {
            if (selected.getEnrolledCount() == 0) {
                System.out.println("  Cannot remove members. No one is currently enrolled in this class.");
                return;
            }
        }

        Member[] members = club.getMembers();
        if (members.length == 0) {
            System.out.println("  Error: No registered members found.");
            return;
        }

        System.out.println("\n  Registered Members:");
        System.out.println(String.format("  %-4s  %-16s  %s", "No.", "Name", "ID"));
        System.out.println("  " + repeat('-', 32));
        for (int i = 0; i < members.length; i++) {
            System.out.println(String.format("  %-4d  %-16s  %s",
                    i + 1, members[i].getUserName(), members[i].getUserID()));
        }
        int mi = getIntInputRange("  Select member (1-" + members.length + "): ", 1, members.length) - 1;
        Member chosen = members[mi];

        String actionStr = action == 1 ? "assign" : "remove";
        coach.manageMember(chosen, selected, actionStr, club);
    }

    static void coachViewProgress() {
        printHeader("VIEW MEMBER PROGRESS");
        Member[] members = club.getMembers();
        if (members.length == 0) {
            System.out.println("  No registered members.");
            return;
        }

        System.out.println("  Select member:");
        System.out.println(String.format("  %-4s  %-16s  %s", "No.", "Name", "ID"));
        System.out.println("  " + repeat('-', 32));
        for (int i = 0; i < members.length; i++) {
            System.out.println(String.format("  %-4d  %-16s  %s",
                    i + 1, members[i].getUserName(), members[i].getUserID()));
        }
        int mi = getIntInputRange("  Select (1-" + members.length + "): ", 1, members.length) - 1;
        Member selectedMember = members[mi];

        System.out.println("\n  Progress records for " + selectedMember.getUserName()
                + " (" + selectedMember.getUserID() + "):");
        System.out.println("  " + repeat('-', 50));
        boolean found = false;
        Progress[] progresses = club.getProgresses();
        String[] pOwner = club.getProgressOwner();
        for (int i = 0; i < club.getProgressCount(); i++) {
            if (pOwner[i].equals(selectedMember.getUserID())) {
                System.out.println(progresses[i]);
                System.out.println("  " + repeat('-', 50));
                found = true;
            }
        }
        if (!found)
            System.out.println("  No progress records yet for this member.");
    }

    // ================================================================
    // MODEL UI: MEMBER
    // ================================================================
    static void memberMenu(Member member) {
        boolean inMenu = true;
        while (inMenu) {
            printHeader("MEMBER MENU  -  " + member.getUserName() + "  [" + member.getUserID() + "]");
            System.out.println("  1. View / Renew Membership");
            System.out.println("  2. Book Fitness Class");
            System.out.println("  3. Track My Progress");
            System.out.println("  4. Calculate BMI");
            System.out.println("  5. View My Bookings");
            System.out.println("  0. Logout");
            int choice = getIntInputRange("\n  Your choice (0-5): ", 0, 5);
            switch (choice) {
                case 1:
                    memberViewMembership(member);
                    break;
                case 2:
                    memberBookClass(member);
                    break;
                case 3:
                    memberTrackProgress(member);
                    break;
                case 4:
                    memberCalculateBMI(member);
                    break;
                case 5:
                    memberViewBookings(member);
                    break;
                case 0:
                    System.out.println("  Logged out.");
                    inMenu = false;
                    break;
            }
        }
    }

    static void memberViewMembership(Member member) {
        printHeader("MY MEMBERSHIP  -  " + member.getUserName());
        System.out.println(member);
        System.out.println("\n  1. Renew Membership / Make Payment");
        System.out.println("  0. Back");
        int choice = getIntInputRange("  Your choice (0-1): ", 0, 1);
        if (choice == 1) {
            memberMakePayment(member);
        }
    }

    static void memberMakePayment(Member member) {
        printHeader("MAKE PAYMENT  -  " + member.getUserName());
        System.out.println(String.format("  %-4s  %-10s  %s", "No.", "Plan", "Price"));
        System.out.println("  " + repeat('-', 36));
        System.out.println(String.format("  %-4s  %-10s  %s", "1.", "BASIC", "RM  99.90 / month"));
        System.out.println(String.format("  %-4s  %-10s  %s", "2.", "PREMIUM", "RM 199.90 / month"));
        System.out.println(String.format("  %-4s  %-10s  %s", "3.", "VIP", "RM 299.90 / month"));
        int pc = getIntInputRange("  Select plan (1-3): ", 1, 3);
        double amount = (pc == 1) ? 99.90 : (pc == 2) ? 199.90 : 299.90;
        MembershipType mType = (pc == 1) ? MembershipType.BASIC
                : (pc == 2) ? MembershipType.PREMIUM : MembershipType.VIP;

        System.out.println("\n  Payment Methods:");
        System.out.println("  1. Credit Card    2. Online Banking    3. Cash");
        int methodC = getIntInputRange("  Select method (1-3): ", 1, 3);
        String method = (methodC == 1) ? "Credit Card" : (methodC == 2) ? "Online Banking" : "Cash";

        String payID = "PAY-" + member.getUserID() + "-" + System.currentTimeMillis();
        Payment payment = new Payment(payID, amount, null, method);
        payment.makePayment();

        member.makePayment(amount, mType, payment, club);
    }

    static void memberBookClass(Member member) {
        printHeader("BOOK FITNESS CLASS  -  " + member.getUserName());
        if (club.getClassCount() == 0) {
            System.out.println("  No classes available yet.");
            return;
        }

        System.out.println(club.getWeeklySchedule());
        int ci = getIntInputRange("\n  Class number to book (1-" + club.getClassCount() + "): ", 1,
                club.getClassCount()) - 1;
        FitnessClass selected = club.getClasses()[ci];

        Member[] enrolled = selected.getEnrolledMembers();
        for (int i = 0; i < selected.getEnrolledCount(); i++) {
            if (member.equals(enrolled[i])) {
                System.out.println("  Error: You are already enrolled in \"" + selected.getName() + "\".");
                return;
            }
        }

        member.bookClass(selected, club);
    }

    static void memberTrackProgress(Member member) {
        printHeader("MY PROGRESS  -  " + member.getUserName());
        System.out.println("  Progress History:");
        System.out.println("  " + repeat('-', 50));
        boolean hasHistory = false;
        Progress lastEntry = null;

        Progress[] progresses = club.getProgresses();
        String[] progressOwner = club.getProgressOwner();
        for (int i = 0; i < club.getProgressCount(); i++) {
            if (progressOwner[i].equals(member.getUserID())) {
                System.out.println(progresses[i]);
                System.out.println("  " + repeat('-', 50));
                lastEntry = progresses[i];
                hasHistory = true;
            }
        }
        if (!hasHistory) {
            System.out.println("  No records yet.");
            System.out.println("  Starting weight (from registration): " + member.getWeight() + " kg");
        }

        System.out.println("\n  --- Record New Entry ---");
        double newWeight = getDoubleInput("  Current weight (kg): ");
        System.out.print("  Notes              : ");
        String notes = sc.nextLine().trim();

        double baseWeight = (lastEntry != null) ? lastEntry.getWeight() : member.getWeight();

        member.recordProgress(newWeight, notes, club);

        double change = newWeight - baseWeight;
        String msg = (change < 0) ? "(Well done! Weight lost.)"
                : (change > 0) ? "(Weight gained.)" : "(Unchanged.)";
        System.out.printf("  Change since %s: %+.1f kg  %s%n",
                (lastEntry == null ? "registration" : "last record"), change, msg);
    }

    static void memberCalculateBMI(Member member) {
        printHeader("BMI CALCULATOR  -  " + member.getUserName());
        System.out.println("  Current Weight: " + member.getWeight() + " kg");
        System.out.println("  Current Height: " + member.getHeight() + " m");
        System.out.println("\n  1. Use current data");
        System.out.println("  2. Enter new values");
        int choice = getIntInputRange("  Your choice (1-2): ", 1, 2);
        if (choice == 2) {
            double w = getDoubleInput("  Enter weight (kg): ");
            double h = getDoubleInput("  Enter height (m) : ");
            member.setWeight(w);
            member.setHeight(h);
        }
        member.calculateBMI();
    }

    static void memberViewBookings(Member member) {
        printHeader("MY BOOKINGS  -  " + member.getUserName());
        boolean found = false;
        System.out.println(String.format("  %-12s  %-26s  %s", "Booking ID", "Class", "Status"));
        System.out.println("  " + repeat('-', 58));

        Booking[] bookings = club.getBookings();
        String[] bOwner = club.getBookingOwner();
        String[] bClassId = club.getBookingClassId();
        FitnessClass[] classes = club.getClasses();

        for (int i = 0; i < club.getBookingCount(); i++) {
            if (bOwner[i].equals(member.getUserID())) {
                String cname = "Unknown";
                for (int j = 0; j < club.getClassCount(); j++) {
                    if (String.valueOf(classes[j].getClassId()).equals(bClassId[i])) {
                        cname = classes[j].getName() + " (" + classes[j].getDate() + " " + classes[j].getTime() + ")";
                        break;
                    }
                }
                System.out.println(String.format("  %-12d  %-26s  %s",
                        bookings[i].getBookingID(), cname, bookings[i].getStatus()));
                found = true;
            }
        }
        if (!found)
            System.out.println("  No bookings found.");
    }

    // ================================================================
    // UTILS
    // ================================================================
    static void printHeader(String title) {
        System.out.println("\n============================================================");
        System.out.println("  " + title);
        System.out.println("============================================================");
    }

    static void printBanner() {
        printHeader("WELCOME TO Y FITNESS SYSTEM");
        System.out.println("  Preset Accounts:");
        System.out.println(String.format("  %-8s  ID: %-6s  Password: %s", "Staff:", "S001", "staff123"));
        System.out.println(String.format("  %-8s  ID: %-6s  Password: %s", "Admin:", "A001", "admin123"));
        System.out.println("  Members & Coaches: Please register first.");
    }

    static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++)
            sb.append(c);
        return sb.toString();
    }

    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a valid whole number.");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    static int getIntInputRange(String prompt, int min, int max) {
        while (true) {
            int val = getIntInput(prompt);
            if (val >= min && val <= max)
                return val;
            System.out.println("  [!] Invalid option. Please enter a number between " + min + " and " + max + ".");
        }
    }
}
