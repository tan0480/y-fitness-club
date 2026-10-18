public class FitnessClass {
    private int classId;
    private String name;
    private ClassType type;
    private String date;
    private String time;
    private int capacity;
    private int durationMins;
    private String location;
    private Member[] enrolledMembers;
    private int enrolledCount;

    // No-arg constructor
    public FitnessClass() {
        this.classId = 0;
        this.name = "";
        this.type = null;
        this.date = "";
        this.time = "";
        this.capacity = 0;
        this.durationMins = 0;
        this.location = "";
        this.enrolledMembers = new Member[0];
        this.enrolledCount = 0;
    }

    // Parameterized constructor
    public FitnessClass(int classId, String name, ClassType type, String date, String time,
            int capacity, int durationMins, String location) {
        this.classId = classId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.durationMins = durationMins;
        this.location = location;
        this.enrolledMembers = new Member[capacity];
        this.enrolledCount = 0;
        System.out.println("New class slot created: \"" + name + "\" (" + type + ")"
                + " | Date: " + date + " " + time
                + " | Location: " + location
                + " | Duration: " + durationMins + " mins"
                + " | Capacity: " + capacity);
    }

    public boolean enroll(Member member) {
        if (enrolledCount >= capacity) {
            System.out.println("Enrollment failed. Class \"" + name + "\" is full.");
            return false;
        }
        enrolledMembers[enrolledCount] = member;
        enrolledCount++;
        System.out.println(member.getUserName() + " has registered for class \""
                + name + "\". Available slots remaining: " + getAvailableSlots());
        return true;
    }

    public void removeMember(Member member) {
        int index = -1;
        for (int i = 0; i < enrolledCount; i++) {
            if (enrolledMembers[i].getUserID().equals(member.getUserID())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Remove failed. " + member.getUserName()
                    + " is not enrolled in class \"" + name + "\".");
            return;
        }
        for (int i = index; i < enrolledCount - 1; i++) {
            enrolledMembers[i] = enrolledMembers[i + 1];
        }
        enrolledMembers[enrolledCount - 1] = null;
        enrolledCount--;
        System.out.println(member.getUserName() + " has been removed from class \""
                + name + "\". Available slots remaining: " + getAvailableSlots());
    }

    public void viewMembers() {
        System.out.println("Class \"" + name + "\" (" + type + ") — Enrolled: "
                + enrolledCount + "/" + capacity);
        if (enrolledCount == 0) {
            System.out.println("  No members enrolled yet.");
        } else {
            for (int i = 0; i < enrolledCount; i++) {
                System.out.println("  " + (i + 1) + ". " + enrolledMembers[i].getUserName());
            }
        }
    }

    public boolean isFull() { return enrolledCount >= capacity; }
    public int getAvailableSlots() { return capacity - enrolledCount; }

    // Getters
    public int getClassId() { return classId; }
    public String getName() { return name; }
    public ClassType getType() { return type; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getCapacity() { return capacity; }
    public int getDurationMins() { return durationMins; }
    public String getLocation() { return location; }
    public Member[] getEnrolledMembers() { return enrolledMembers; }
    public int getEnrolledCount() { return enrolledCount; }

    // Setters
    public void setClassId(int classId) { this.classId = classId; }
    public void setName(String name) { this.name = name; }
    public void setType(ClassType type) { this.type = type; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setDurationMins(int durationMins) { this.durationMins = durationMins; }
    public void setLocation(String location) { this.location = location; }
    public void setEnrolledMembers(Member[] enrolledMembers) { this.enrolledMembers = enrolledMembers; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }

    @Override
    public String toString() {
        return "Class ID: " + classId +
                "\nName    : " + name + " (" + type + ")" +
                "\nDate    : " + date + " " + time +
                "\nLocation: " + location +
                "\nDuration: " + durationMins + " mins" +
                "\nEnrolled: " + enrolledCount + "/" + capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitnessClass)) return false;
        FitnessClass other = (FitnessClass) o;
        return this.classId == other.classId;
    }
}
