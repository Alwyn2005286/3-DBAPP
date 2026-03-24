import java.time.LocalDate;

public class Inspection {
    private int inspectionId;
    private LocalDate inspectionDate;
    private float score;
    private String grade; 
    private String remarks;
    private int establishmentId;
    private int assignmentId;
    private int violationId;
    
    public Inspection(int inspectionId, LocalDate inspectionDate, float score, 
                     String grade, String remarks, int establishmentId, int assignmentId, int violationId) {
        this.inspectionId = inspectionId;
        this.inspectionDate = inspectionDate;
        this.score = score;
        this.grade = grade;
        this.remarks = remarks;
        this.establishmentId = establishmentId;
        this.assignmentId = assignmentId;
        this.violationId = violationId;
    }
    
    // Getters and Setters
    public int getInspectionId() { return inspectionId; }
    public void setInspectionId(int inspectionId) { this.inspectionId = inspectionId; }
    
    public LocalDate getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(LocalDate inspectionDate) { this.inspectionDate = inspectionDate; }
    
    public float getScore() { return score; }
    public void setScore(float score) { this.score = score; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public int getEstablishmentId() { return establishmentId; }
    public void setEstablishmentId(int establishmentId) { this.establishmentId = establishmentId; }
    
    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }
    
    public int getViolationId() { return violationId; }
    public void setViolationId(int violationId) { this.violationId = violationId; }
    
}