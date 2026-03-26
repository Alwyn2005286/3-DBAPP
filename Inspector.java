public class Inspector {
    private int inspectorId;
    private String firstName;
    private String lastName;

    public Inspector(int inspectorId, String firstName, String lastName) {
        this.inspectorId = inspectorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getInspectorId() { return inspectorId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}