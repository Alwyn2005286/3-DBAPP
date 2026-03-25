import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViolationDAO {

    public void addViolation(Violation v) {
        String sql = "INSERT INTO violations (Requirement_Code, Inspection_ID) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, v.getRequirementCode());
            stmt.setInt(2, v.getInspectionId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Violation> getAllViolations() {
        List<Violation> list = new ArrayList<>();
        String sql = "SELECT * FROM violations";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Violation v = new Violation(
                        rs.getInt("Violation_Id"),
                        rs.getInt("Requirement_Code"),
                        rs.getInt("Inspection_ID")
                );
                list.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateViolation(Violation v) {
        String sql = "UPDATE violations SET Requirement_Code = ?, Inspection_ID = ? WHERE Violation_Id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, v.getRequirementCode());
            stmt.setInt(2, v.getInspectionId());
            stmt.setInt(3, v.getViolationId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteViolation(int id) {
        String sql = "DELETE FROM violations WHERE Violation_Id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}