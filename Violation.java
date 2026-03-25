public class Violation {

    private int violationCode;
    private int requirementCode;
    private String inspectorRemarks;
    private String requirementStatus;

    public Violation(int violationCode, int requirementCode, String inspectorRemarks, String requirementStatus) {
        this.violationCode = violationCode;
        this.requirementCode = requirementCode;
        this.inspectorRemarks = inspectorRemarks;
        this.requirementStatus = requirementStatus;
    }

    // Getters and Setters

    public int getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(int violationCode) {
        this.violationCode = violationCode;
    }

    public int getRequirementCode() {
        return requirementCode;
    }

    public void setRequirementCode(int requirementCode) {
        this.requirementCode = requirementCode;
    }

    public String getInspectorRemarks() {
        return inspectorRemarks;
    }

    public void setInspectorRemarks(String inspectorRemarks) {
        this.inspectorRemarks = inspectorRemarks;
    }

    public String getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(String requirementStatus) {
        this.requirementStatus = requirementStatus;
    }
}