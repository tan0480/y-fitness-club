import java.util.Date;

public class Staff extends User {
    private String staffRole;
    private String shiftSchedule;

    public Staff() {
        super();
        this.staffRole = "General Staff";
        this.shiftSchedule = "Not Assigned";
    }

    public Staff(String userID, String userName, String phone, String email,
            String password, String role, Gender gender,
            String staffRole, String shiftSchedule) {
        super(userID, userName, phone, email, password, role, gender);
        this.staffRole = staffRole;
        this.shiftSchedule = shiftSchedule;
    }

    // ================================================================
    // BUSINESS METHODS
    // ================================================================

    public void changeEquipmentStatus(Equipment target, EquipStatus newStatus) {
        if (target.getStatus() == newStatus) {
            System.out.println("  [" + target.getName() + "] is already " + newStatus + ". No change made.");
            return;
        }

        if (target.getStatus() == EquipStatus.IN_USE && (newStatus == EquipStatus.MAINTENANCE || newStatus == EquipStatus.BROKEN)) {
            System.out.println("  Cannot change status to " + newStatus + " because the equipment is currently IN_USE.");
            return;
        }

        target.setStatus(newStatus);
        if (newStatus == EquipStatus.MAINTENANCE || newStatus == EquipStatus.BROKEN) {
            target.setLastMaintenance(new Date());
            this.flagEquipmentMaintainance(target.getEquipmentId());
        }
        System.out.println("\n  Status updated successfully!");
        System.out.println(target);
    }

    public void arrangeShift(Shift shift, FitnessClub club) {
        club.addShift(shift, this.getUserID());
        this.updateShiftSchedule(shift.getShiftType() + " " + shift.getStartTime() + "-" + shift.getEndTime());
        shift.getWorkingHours(shift.getStartTime(), shift.getEndTime());
        System.out.println("\n  Shift arranged:");
        System.out.println(shift);
    }

    public void updateShiftSchedule(String newShift) {
        this.shiftSchedule = newShift;
        System.out.println("Staff " + getUserName() + "'s shift schedule updated to: " + newShift);
    }

    public void flagEquipmentMaintainance(int equipmentId) {
        System.out.println("Staff " + getUserName() + " flagged equipment ID " + equipmentId + " for maintenance/issue.");
    }

    // Getters and Setters
    public String getStaffRole() { return staffRole; }
    public void setStaffRole(String staffRole) { this.staffRole = staffRole; }
    public String getShiftSchedule() { return shiftSchedule; }
    public void setShiftSchedule(String shiftSchedule) { this.shiftSchedule = shiftSchedule; }

    @Override
    public String toString() {
        return super.toString() +
                "\nStaff Role: " + staffRole +
                "\nShift Schedule: " + shiftSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        return super.equals(o);
    }
}
