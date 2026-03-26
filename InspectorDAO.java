import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InspectorDAO {
    
    public List<Inspector> getAllInspectors() {
        List<Inspector> inspectors = new ArrayList<>();
        String query = "SELECT Inspector_ID, First_Name, Last_Name FROM Inspectors";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inspector inspector = new Inspector(
                    rs.getInt("Inspector_ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name")
                );
                inspectors.add(inspector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspectors;
    }

    public void addInspector(Inspector inspector) {
        String query = "INSERT INTO Inspectors (Inspector_ID, First_Name, Last_Name) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspector.getInspectorId());
            stmt.setString(2, inspector.getFirstName());
            stmt.setString(3, inspector.getLastName());
            
            stmt.executeUpdate();
            System.out.println("Inspector added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding inspector: " + e.getMessage());
        }
    }
}