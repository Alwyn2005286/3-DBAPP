import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignedInspectorDAO {
    public List<AssignedInspector> getAllAssignments() {
        List<AssignedInspector> list = new ArrayList<>();
        String query = "SELECT assignment_id, inspection_id, inspector_id FROM Assigned_Inspectors";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new AssignedInspector(rs.getInt("assignment_id"), rs.getInt("inspection_id"), rs.getInt("inspector_id")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void addAssignment(AssignedInspector ai) {
        String query = "INSERT INTO Assigned_Inspectors (inspection_id, inspector_id) VALUES (?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ai.getInspectionId());
            stmt.setInt(2, ai.getInspectorId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public List<Integer> getAvailableInspectorIds() {
    List<Integer> ids = new ArrayList<>();
    String query = "SELECT inspector_id FROM Inspectors";
    try (Connection conn = DataBaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) ids.add(rs.getInt("inspector_id"));
    } catch (Exception e) { e.printStackTrace(); }
    return ids;
    }
    public List<Integer> getAvailableInspectionIds() {
    List<Integer> ids = new ArrayList<>();
    String query = "SELECT inspection_id FROM Inspections";
    try (Connection conn = DataBaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) ids.add(rs.getInt("inspection_id"));
    } catch (Exception e) { e.printStackTrace(); }
    return ids;
    }
}