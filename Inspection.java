import java.math.BigDecimal;
import java.time.LocalDate;

public class Inspection {
    private int inspectionId;
    private int violationCode;
    private int assignmentId;
    private LocalDate inspectionDate;
    private String remarks;
    private BigDecimal score;
    private String grade;

    public Inspection(int inspectionId, int violationCode, int assignmentId, LocalDate inspectionDate, String remarks, BigDecimal score, String grade) {
        this.inspectionId = inspectionId;
        this.violationCode = violationCode;
        this.assignmentId = assignmentId;
        this.inspectionDate = inspectionDate;
        this.remarks = remarks;
        this.score = score;
        this.grade = grade;
    }

    // Getters and Setters
    public int getInspectionId() { return inspectionId; }
    public void setInspectionId(int inspectionId) { this.inspectionId = inspectionId; }

    public int getViolationCode() { return violationCode; }
    public void setViolationCode(int violationCode) { this.violationCode = violationCode; }

    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }

    public LocalDate getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(LocalDate inspectionDate) { this.inspectionDate = inspectionDate; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public BigDecimal getScore() { return score; }
    public void setScore(BigDecimal score) { this.score = score; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}