import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InspectionRequirementDAO {
    
    public List<InspectionRequirement> getAllRequirements() {
        List<InspectionRequirement> requirements = new ArrayList<>();
        String query = "SELECT Requirement_Code, Title, Standard_Fine, Description, Weight FROM REF_Inspection_Requirements";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                InspectionRequirement req = new InspectionRequirement(
                    rs.getInt("Requirement_Code"),
                    rs.getString("Title"),
                    rs.getDouble("Standard_Fine"),
                    rs.getString("Description"),
                    rs.getInt("Weight")
                );
                requirements.add(req);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requirements;
    }

    public void addRequirement(InspectionRequirement req) {
        String query = "INSERT INTO REF_Inspection_Requirements (Requirement_Code, Title, Standard_Fine, Description, Weight) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, req.getRequirementCode());
            stmt.setString(2, req.getTitle());
            stmt.setDouble(3, req.getStandardFine());
            stmt.setString(4, req.getDescription());
            stmt.setInt(5, req.getWeight());
            
            stmt.executeUpdate();
            System.out.println("Requirement added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding requirement: " + e.getMessage());
        }
    }
}