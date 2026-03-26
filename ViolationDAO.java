import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViolationDAO {
    public List<Violation> getAllViolations() {
        List<Violation> list = new ArrayList<>();
        String query = "SELECT violation_id, inspection_id, Requirement_Code, remarks, status FROM Violations";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Violation(
                    rs.getInt("violation_id"),
                    rs.getInt("inspection_id"),
                    rs.getInt("Requirement_Code"),
                    rs.getString("remarks"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public void addViolation(Violation v) {
        String query = "INSERT INTO Violations (inspection_id, Requirement_Code, remarks, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, v.getInspectionId());
            stmt.setInt(2, v.getRequirementCode());
            stmt.setString(3, v.getRemarks());
            stmt.setString(4, v.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Integer> getAvailableRequirementCodes() {
        List<Integer> codes = new ArrayList<>();
        String query = "SELECT Requirement_Code FROM REF_Inspection_Requirements";
        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) codes.add(rs.getInt("Requirement_Code"));
        } catch (Exception e) { e.printStackTrace(); }
        return codes;
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