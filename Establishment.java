public class Establishment {
    private int id, ownerId, cityId; // cityId is now an int
    private String name;

    public Establishment(int id, int ownerId, String name, int cityId) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.cityId = cityId;
    }

    public int getEstablishmentId() { return id; }
    public int getOwnerId() { return ownerId; }
    public String getEstablishmentName() { return name; }
    public int getCityId() { return cityId; } // city_id from SQL
}