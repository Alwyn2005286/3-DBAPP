import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViolationDAO {

    // CREATE
    public static int addViolation(Violation violation) throws SQLException {
        String query = "INSERT INTO violations (Requirement_Code, Inspection_ID) VALUES (?, ?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1, violation.getRequirementCode());
        stmt.setInt(2, violation.getInspectionId());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        int generatedId = -1;

        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        rs.close();
        stmt.close();
        conn.close();

        return generatedId;
    }

    // READ ALL
    public static List<Violation> getAllViolations() throws SQLException {
        List<Violation> violations = new ArrayList<>();

        String query = "SELECT * FROM violations ORDER BY Violation_Id DESC";

        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Violation v = new Violation(
                rs.getInt("Violation_Id"),
                rs.getInt("Requirement_Code"),
                rs.getInt("Inspection_ID")
            );

            violations.add(v);
        }

        rs.close();
        stmt.close();
        conn.close();

        return violations;
    }

    // UPDATE
    public static boolean updateViolation(Violation violation) throws SQLException {
        String query = "UPDATE violations SET Requirement_Code = ?, Inspection_ID = ? WHERE Violation_Id = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setInt(1, violation.getRequirementCode());
        stmt.setInt(2, violation.getInspectionId());
        stmt.setInt(3, violation.getViolationId());

        int rowsAffected = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rowsAffected > 0;
    }

    // DELETE
    public static boolean deleteViolation(int violationId) throws SQLException {
        String query = "DELETE FROM violations WHERE Violation_Id = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setInt(1, violationId);

        int rowsAffected = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rowsAffected > 0;
    }
}