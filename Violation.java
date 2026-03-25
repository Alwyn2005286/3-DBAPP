public class Violation {

    private int violationId;
    private int requirementCode;
    private int inspectionId;

    public Violation() {}

    public Violation(int requirementCode, int inspectionId) {
        this.requirementCode = requirementCode;
        this.inspectionId = inspectionId;
    }

    public Violation(int violationId, int requirementCode, int inspectionId) {
        this.violationId = violationId;
        this.requirementCode = requirementCode;
        this.inspectionId = inspectionId;
    }

    public int getViolationId() {
        return violationId;
    }

    public void setViolationId(int violationId) {
        this.violationId = violationId;
    }

    public int getRequirementCode() {
        return requirementCode;
    }

    public void setRequirementCode(int requirementCode) {
        this.requirementCode = requirementCode;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }
}