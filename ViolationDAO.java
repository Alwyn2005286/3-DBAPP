import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViolationDAO {

    // CREATE
    public static boolean addViolation(Violation violation) throws SQLException {
        String query = "INSERT INTO Violations (Violation_Code, Requirement_Code, Inspector_Remarks, Requirement_Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, violation.getViolationCode());
            stmt.setInt(2, violation.getRequirementCode());
            stmt.setString(3, violation.getInspectorRemarks());
            stmt.setString(4, violation.getRequirementStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // READ ALL
    public static List<Violation> getAllViolations() throws SQLException {
        List<Violation> violations = new ArrayList<>();
        String query = "SELECT * FROM Violations ORDER BY Violation_Code DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Violation v = new Violation(
                    rs.getInt("Violation_Code"),
                    rs.getInt("Requirement_Code"),
                    rs.getString("Inspector_Remarks"),
                    rs.getString("Requirement_Status")
                );
                violations.add(v);
            }
        }
        return violations;
    }
    
    // READ ONE
    public static Violation getViolationByCode(int violationCode) throws SQLException {
        String query = "SELECT * FROM Violations WHERE Violation_Code = ?";
        Violation violation = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, violationCode);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    violation = new Violation(
                        rs.getInt("Violation_Code"),
                        rs.getInt("Requirement_Code"),
                        rs.getString("Inspector_Remarks"),
                        rs.getString("Requirement_Status")
                    );
                }
            }
        }
        return violation;
    }

    // UPDATE
    public static boolean updateViolation(Violation violation) throws SQLException {
        String query = "UPDATE Violations SET Requirement_Code = ?, Inspector_Remarks = ?, Requirement_Status = ? WHERE Violation_Code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, violation.getRequirementCode());
            stmt.setString(2, violation.getInspectorRemarks());
            stmt.setString(3, violation.getRequirementStatus());
            stmt.setInt(4, violation.getViolationCode());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // DELETE
    public static boolean deleteViolation(int violationCode) throws SQLException {
        String query = "DELETE FROM Violations WHERE Violation_Code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, violationCode);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}