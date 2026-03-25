
public class Establishment {

    private int establishmentId;
    private String establishmentName;
    private String ownerName;
    private String address;
    private String contactInfo;
    private String operatingStatus; // OPEN, CLOSED, SUSPENDED

    public Establishment(int establishmentId, String establishmentName, String ownerName, 
                         String address, String contactInfo, String operatingStatus) {
        this.establishmentId = establishmentId;
        this.establishmentName = establishmentName;
        this.ownerName = ownerName;
        this.address = address;
        this.contactInfo = contactInfo;
        this.operatingStatus = operatingStatus;
    }

    // Getters and Setters
    public int getEstablishmentId() { return establishmentId; }
    public void setEstablishmentId(int establishmentId) { this.establishmentId = establishmentId; }

    public String getEstablishmentName() { return establishmentName; }
    public void setEstablishmentName(String establishmentName) { this.establishmentName = establishmentName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getOperatingStatus() { return operatingStatus; }
    public void setOperatingStatus(String operatingStatus) { this.operatingStatus = operatingStatus; }
}
