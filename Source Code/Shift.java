import java.util.Date;

public class Shift {
    private String shiftID;
    private Date date;
    private String startTime;
    private String endTime;
    private String shiftType;

    // No-arg constructor
    public Shift() {
        this.shiftID = "";
        this.date = null;
        this.startTime = "";
        this.endTime = "";
        this.shiftType = "";
    }

    // Parameterized constructor
    public Shift(String shiftID, Date date, String startTime, String endTime, String shiftType) {
        this.shiftID = shiftID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
    }

    // Track employee attendance by ID
    public void trackEmployeeAttendance(String employeeID) {
        System.out.println("Attendance recorded for Employee ID: " + employeeID
                + " | Shift: " + shiftID
                + " | Date: " + date
                + " | Time: " + startTime + " - " + endTime
                + " | Type: " + shiftType);
    }

    // Calculate and display working hours for this shift
    public void getWorkingHours(String start, String end) {
        try {
            int startHour = Integer.parseInt(start.split(":")[0]);
            int startMin  = Integer.parseInt(start.split(":")[1]);
            int endHour   = Integer.parseInt(end.split(":")[0]);
            int endMin    = Integer.parseInt(end.split(":")[1]);

            int totalMins = (endHour * 60 + endMin) - (startHour * 60 + startMin);
            if (totalMins < 0) {
                throw new IllegalArgumentException("End time cannot be before start time.");
            }
            int hours = totalMins / 60;
            int mins  = totalMins % 60;

            System.out.println("Shift " + shiftID + " working hours: " + hours + "h " + mins + "m");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid time format. Please use HH:MM. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Time format is missing hours or minutes.");
        }
    }

    // Getters
    public String getShiftID()   { return shiftID; }
    public Date getDate()        { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime()   { return endTime; }
    public String getShiftType() { return shiftType; }

    // Setters
    public void setShiftID(String shiftID)     { this.shiftID = shiftID; }
    public void setDate(Date date)             { this.date = date; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime)     { this.endTime = endTime; }
    public void setShiftType(String shiftType) { this.shiftType = shiftType; }

    @Override
    public String toString() {
        return "Shift ID: " + shiftID +
               "\nDate: " + date +
               "\nStart Time: " + startTime +
               "\nEnd Time: " + endTime +
               "\nShift Type: " + shiftType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shift)) return false;
        Shift other = (Shift) o;
        return this.shiftID.equals(other.shiftID);
    }
}
