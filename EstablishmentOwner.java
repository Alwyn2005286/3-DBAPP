public class EstablishmentOwner {
    private int ownerId;
    private String firstName;
    private String lastName;

    public EstablishmentOwner(int ownerId, String firstName, String lastName) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getOwnerId() { return ownerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}