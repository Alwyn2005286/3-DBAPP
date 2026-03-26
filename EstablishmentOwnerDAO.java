import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentOwnerDAO {
    
    public List<EstablishmentOwner> getAllOwners() {
        List<EstablishmentOwner> owners = new ArrayList<>();
        String query = "SELECT Owner_ID, First_Name, Last_Name FROM Establishment_Owners";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstablishmentOwner owner = new EstablishmentOwner(
                    rs.getInt("Owner_ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name")
                );
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owners;
    }

    public void addOwner(EstablishmentOwner owner) {
        String query = "INSERT INTO Establishment_Owners (Owner_ID, First_Name, Last_Name) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, owner.getOwnerId());
            stmt.setString(2, owner.getFirstName());
            stmt.setString(3, owner.getLastName());
            
            stmt.executeUpdate();
            System.out.println("Owner added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding owner: " + e.getMessage());
        }
    }
}