import java.util.Date;

public class WeeklySchedule {
    private String scheduleID;
    private Date weekStartDate;
    private Date weekEndDate;
    private FitnessClass[] classes;
    private int classCount;

    // No-arg constructor
    public WeeklySchedule() {
        this.scheduleID = "";
        this.weekStartDate = null;
        this.weekEndDate = null;
        this.classes = new FitnessClass[50];
        this.classCount = 0;
    }

    // Parameterized constructor
    public WeeklySchedule(String scheduleID, Date weekStartDate, Date weekEndDate) {
        this.scheduleID = scheduleID;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.classes = new FitnessClass[50];
        this.classCount = 0;
    }

    // Add a new class to the weekly schedule
    public void addClass(FitnessClass newClass) {
        if (classCount >= classes.length) {
            System.out.println("  [Error] Schedule is full. Cannot add more classes.");
            return;
        }
        classes[classCount] = newClass;
        classCount++;
        System.out.println("Class \"" + newClass.getName() + "\" added to schedule " + scheduleID
                + ". Total classes: " + classCount);
    }

    // Display all classes in the weekly schedule
    public FitnessClass[] displayWeeklyClasses() {
        System.out.println("===== Weekly Schedule: " + scheduleID + " =====");
        System.out.println("Week: " + weekStartDate + " to " + weekEndDate);
        if (classCount == 0) {
            System.out.println("  No classes scheduled.");
        } else {
            for (int i = 0; i < classCount; i++) {
                System.out.println("  " + (i + 1) + ". " + classes[i].getName()
                        + " (" + classes[i].getType() + ")"
                        + " | Location: " + classes[i].getLocation()
                        + " | Duration: " + classes[i].getDurationMins() + " mins"
                        + " | Slots: " + classes[i].getAvailableSlots() + "/" + classes[i].getCapacity());
            }
        }
        return classes;
    }

    // Getters
    public String getScheduleID() {
        return scheduleID;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public FitnessClass[] getClasses() {
        return classes;
    }

    public int getClassCount() {
        return classCount;
    }

    // Setters
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public void setClasses(FitnessClass[] classes) {
        this.classes = classes;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule ID: ").append(scheduleID)
                .append("\nWeek Start: ").append(weekStartDate)
                .append("\nWeek End: ").append(weekEndDate)
                .append("\nTotal Classes: ").append(classCount);
        for (int i = 0; i < classCount; i++) {
            sb.append("\n  ").append(i + 1).append(". ").append(classes[i].getName())
                    .append(" (").append(classes[i].getType()).append(")");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WeeklySchedule))
            return false;
        WeeklySchedule other = (WeeklySchedule) o;
        return this.scheduleID.equals(other.scheduleID);
    }
}
