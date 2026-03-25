public class Establishment {

    private int establishmentId;
    private int ownerId;
    private String establishmentName;
    private String cityName;

    public Establishment(int establishmentId, int ownerId, String establishmentName, String cityName) {
        this.establishmentId = establishmentId;
        this.ownerId = ownerId;
        this.establishmentName = establishmentName;
        this.cityName = cityName;
    }

    // Getters and Setters

    public int getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
