public class Inspector {

    private int inspectorId;
    private String firstName;
    private String lastName;

    public Inspector(int inspectorId, String firstName, String lastName) {
        this.inspectorId = inspectorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
