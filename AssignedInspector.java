public class AssignedInspector {
    private int assignmentId, inspectionId, inspectorId;

    public AssignedInspector(int assignmentId, int inspectionId, int inspectorId) {
        this.assignmentId = assignmentId;
        this.inspectionId = inspectionId;
        this.inspectorId = inspectorId;
    }

    public int getAssignmentId() { return assignmentId; }
    public int getInspectionId() { return inspectionId; }
    public int getInspectorId() { return inspectorId; }
}