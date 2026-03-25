import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDAO {
    public List<Establishment> getAllEstablishments() {
        List<Establishment> establishments = new ArrayList<>();
        // Updated to use city_id per the new SQL file
        String query = "SELECT establishment_id, owner_id, establishment_name, city_id FROM Establishments";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                establishments.add(new Establishment(
                    rs.getInt("establishment_id"),
                    rs.getInt("owner_id"),
                    rs.getString("establishment_name"),
                    rs.getInt("city_id") // Changed from String to Int
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return establishments;
    }

    public void addEstablishment(Establishment est) {
        String query = "INSERT INTO Establishments (owner_id, establishment_name, city_id) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, est.getOwnerId());
            stmt.setString(2, est.getEstablishmentName());
            stmt.setInt(3, est.getCityId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Integer> getAvailableCityIds() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT city_id FROM REF_Cities";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) { ids.add(rs.getInt("city_id")); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ids;
    }

    public List<Integer> getAvailableOwnerIds() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT owner_id FROM Establishment_Owners";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) { ids.add(rs.getInt("owner_id")); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ids;
    }
}