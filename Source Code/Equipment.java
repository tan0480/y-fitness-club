import java.util.Date;

public class Equipment {
    private int equipmentId;
    private String name;
    private String category;
    private int totalQuantity;
    private int availableQuantity;
    private EquipStatus status;
    private Date lastMaintenance;

    public Equipment() {
        this.equipmentId = 0;
        this.name = "";
        this.category = "";
        this.totalQuantity = 0;
        this.availableQuantity = 0;
        this.status = EquipStatus.AVAILABLE;
        this.lastMaintenance = null;
    }

    public Equipment(int equipmentId, String name, String category, int totalQuantity,
                     EquipStatus status, Date lastMaintenance) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.category = category;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
        this.status = status;
        this.lastMaintenance = lastMaintenance;
    }

    public void reduceAvailable(int qty) {
        if (this.availableQuantity >= qty) {
            this.availableQuantity -= qty;
        }
    }

    public void increaseAvailable(int qty) {
        if (this.availableQuantity + qty <= this.totalQuantity) {
            this.availableQuantity += qty;
        }
    }

    // Report an issue — sets status to BROKEN
    public void reportIssue() {
        this.status = EquipStatus.BROKEN;
        System.out.println("Issue reported for \"" + name + "\" (ID: " + equipmentId
                + "). Status changed to BROKEN.");
    }

    // Schedule maintenance — sets status to MAINTENANCE and records the date
    public void scheduleMaintenance() {
        this.status = EquipStatus.MAINTENANCE;
        this.lastMaintenance = new Date();
        System.out.println("Maintenance scheduled for \"" + name + "\" (ID: " + equipmentId
                + "). Last maintenance date: " + lastMaintenance);
    }

    // Getters
    public int getEquipmentId()        { return equipmentId; }
    public String getName()            { return name; }
    public String getCategory()        { return category; }
    public int getTotalQuantity()      { return totalQuantity; }
    public int getAvailableQuantity()  { return availableQuantity; }
    public EquipStatus getStatus()     { return status; }
    public Date getLastMaintenance()   { return lastMaintenance; }

    // Setters
    public void setEquipmentId(int equipmentId)           { this.equipmentId = equipmentId; }
    public void setName(String name)                      { this.name = name; }
    public void setCategory(String category)              { this.category = category; }
    public void setTotalQuantity(int totalQuantity)       { this.totalQuantity = totalQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setStatus(EquipStatus status)             { this.status = status; }
    public void setLastMaintenance(Date lastMaintenance)  { this.lastMaintenance = lastMaintenance; }

    @Override
    public String toString() {
        return "Equipment ID: " + equipmentId +
               "\nName: " + name +
               "\nCategory: " + category +
               "\nQuantity: " + availableQuantity + " / " + totalQuantity +
               "\nStatus: " + status +
               "\nLast Maintenance: " + lastMaintenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment other = (Equipment) o;
        return this.equipmentId == other.equipmentId;
    }
}
