public class Coach extends User {
    private String specialization;
    private Member[] assignedMembers;

    public Coach() {
        super();
        this.specialization = "General Fitness";
        this.assignedMembers = new Member[0];
    }

    public Coach(String userID, String userName, String phone, String email,
                 String password, String role, Gender gender,
                 String specialization, Member[] assignedMembers) {
        super(userID, userName, phone, email, password, role, gender);
        this.specialization = specialization;
        this.assignedMembers = assignedMembers;
    }

    // ================================================================
    // BUSINESS METHODS
    // ================================================================

    public FitnessClass assignFitnessClass(int classId, String name, ClassType type, String date, String time, int capacity, int duration, String location, FitnessClub club, Equipment eq, int eqQty) {
        FitnessClass newClass = new FitnessClass(classId, name, type, date, time, capacity, duration, location);
        
        FitnessClass[] cList = club.getClasses();
        for (int i = 0; i < club.getClassCount(); i++) {
            if (cList[i].equals(newClass)) {
                System.out.println("  Error: Class ID " + classId + " already exists.");
                return null;
            }
        }

        cList[club.getClassCount()] = newClass;
        club.addClassCount();
        club.getWeeklySchedule().addClass(newClass);

        if (eq != null && eqQty > 0) {
            eq.reduceAvailable(eqQty);
            eq.setStatus(EquipStatus.IN_USE);
            System.out.println("  Equipment assigned: " + eqQty + "x " + eq.getName() + " -> IN_USE");
        }
        
        System.out.println("\nCoach " + getUserName() + " scheduled new class: " + name);
        System.out.println(newClass);
        return newClass;
    }

    public void manageMember(Member member, FitnessClass fClass, String action, FitnessClub club) {
        if (action.equalsIgnoreCase("assign")) {
            Member[] enrolled = fClass.getEnrolledMembers();
            for (int i = 0; i < fClass.getEnrolledCount(); i++) {
                if (member.equals(enrolled[i])) {
                    System.out.println("  " + member.getUserName() + " is already enrolled in \"" + fClass.getName() + "\".");
                    return;
                }
            }
            if (member.bookClass(fClass, club)) {
                System.out.println("Coach " + getUserName() + " successfully assigned member via official booking.");
            }
        } else if (action.equalsIgnoreCase("remove")) {
            fClass.removeMember(member);
            System.out.println("Coach " + getUserName() + " removed " + member.getUserName() + " from class " + fClass.getName());
        } else {
            System.out.println("Invalid action.");
        }
    }

    // Getters / Setters
    public String getSpecialization() { return specialization; }
    public Member[] getAssignedMembers() { return assignedMembers; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setAssignedMembers(Member[] assignedMembers) { this.assignedMembers = assignedMembers; }

    @Override
    public String toString() {
        return super.toString() +
               "\nSpecialization: " + specialization +
               "\nAssigned Members Count: " + (assignedMembers != null ? assignedMembers.length : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        return super.equals(o);
    }
}
