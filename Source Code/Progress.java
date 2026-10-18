import java.util.Date;

public class Progress {
    private String progressID;
    private Date date;
    private double weight;
    private String notes;

    // No-arg constructor
    public Progress() {
        this.progressID = "";
        this.date = null;
        this.weight = 0.0;
        this.notes = "";
    }

    // Parameterized constructor
    public Progress(String progressID, Date date, double weight, String notes) {
        this.progressID = progressID;
        this.date = date;
        this.weight = weight;
        this.notes = notes;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
        this.date = new Date();
    }

    public void addNotes(String newNotes) {
        this.notes = newNotes;
    }

    public String getSummary() {
        return "Progress [ID: " + progressID
                + " | Date: " + date
                + " | Weight: " + weight + " kg"
                + " | Notes: " + notes + "]";
    }

    public double getProgressChange(Progress previous) {
        return this.weight - previous.weight;
    }

    // Getters
    public String getProgressID() { return progressID; }
    public Date getDate()          { return date; }
    public double getWeight()      { return weight; }
    public String getNotes()       { return notes; }

    // Setters
    public void setProgressID(String progressID) { this.progressID = progressID; }
    public void setDate(Date date)               { this.date = date; }
    public void setWeight(double weight)         { this.weight = weight; }
    public void setNotes(String notes)           { this.notes = notes; }

    @Override
    public String toString() {
        return "Progress ID: " + progressID +
               "\nDate: " + date +
               "\nWeight: " + weight + " kg" +
               "\nNotes: " + notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress)) return false;
        Progress other = (Progress) o;
        return this.progressID.equals(other.progressID);
    }
}
