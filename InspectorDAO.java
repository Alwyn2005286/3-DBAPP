import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InspectorDAO {

    // CREATE
    public static boolean addInspector(Inspector inspector) throws SQLException {
        String query = "INSERT INTO Inspectors (Inspector_ID, First_Name, Last_Name) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspector.getInspectorId());
            stmt.setString(2, inspector.getFirstName());
            stmt.setString(3, inspector.getLastName());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // READ ALL
    public static List<Inspector> getAllInspectors() throws SQLException {
        List<Inspector> inspectors = new ArrayList<>();
        String query = "SELECT * FROM Inspectors ORDER BY Inspector_ID DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Inspector i = new Inspector(
                    rs.getInt("Inspector_ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name")
                );
                inspectors.add(i);
            }
        }
        return inspectors;
    }
    
    // READ ONE
    public static Inspector getInspectorById(int inspectorId) throws SQLException {
        String query = "SELECT * FROM Inspectors WHERE Inspector_ID = ?";
        Inspector inspector = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, inspectorId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inspector = new Inspector(
                        rs.getInt("Inspector_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name")
                    );
                }
            }
        }
        return inspector;
    }

    // UPDATE
    public static boolean updateInspector(Inspector inspector) throws SQLException {
        String query = "UPDATE Inspectors SET First_Name = ?, Last_Name = ? WHERE Inspector_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, inspector.getFirstName());
            stmt.setString(2, inspector.getLastName());
            stmt.setInt(3, inspector.getInspectorId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // DELETE
    public static boolean deleteInspector(int inspectorId) throws SQLException {
        String query = "DELETE FROM Inspectors WHERE Inspector_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspectorId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
