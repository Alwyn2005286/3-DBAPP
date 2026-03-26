import java.sql.Date;

public class Inspection {
    private int id, establishmentId;
    private Date date;
    private double score;
    private String grade, remarks;

    public Inspection(int id, int establishmentId, Date date, double score, String grade, String remarks) {
        this.id = id;
        this.establishmentId = establishmentId;
        this.date = date;
        this.score = score;
        this.grade = grade;
        this.remarks = remarks;
    }

    public int getInspectionId() { return id; }
    public int getEstablishmentId() { return establishmentId; }
    public Date getInspectionDate() { return date; }
    public double getScore() { return score; }
    public String getGrade() { return grade; }
    public String getRemarks() { return remarks; }
}