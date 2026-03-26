public class Violation {
    private int violationId, inspectionId, requirementCode;
    private String remarks, status;

    public Violation(int vId, int iId, int rCode, String remarks, String status) {
        this.violationId = vId;
        this.inspectionId = iId;
        this.requirementCode = rCode;
        this.remarks = remarks;
        this.status = status;
    }

    public int getViolationId() { return violationId; }
    public int getInspectionId() { return inspectionId; }
    public int getRequirementCode() { return requirementCode; }
    public String getRemarks() { return remarks; }
    public String getStatus() { return status; }
}