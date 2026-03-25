import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InspectionDAO {

    // CREATE
    public static boolean addInspection(Inspection inspection) throws SQLException {
        String query = "INSERT INTO Inspections (Inspection_ID, Violation_Code, Assignment_ID, Inspection_Date, Remarks, Score, Grade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspection.getInspectionId());
            stmt.setInt(2, inspection.getViolationCode());
            stmt.setInt(3, inspection.getAssignmentId());
            stmt.setDate(4, Date.valueOf(inspection.getInspectionDate()));
            stmt.setString(5, inspection.getRemarks());
            stmt.setBigDecimal(6, inspection.getScore());
            stmt.setString(7, inspection.getGrade());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // READ ALL
    public static List<Inspection> getAllInspections() throws SQLException {
        List<Inspection> inspections = new ArrayList<>();
        String query = "SELECT * FROM Inspections ORDER BY Inspection_Date DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Inspection inspection = new Inspection(
                    rs.getInt("Inspection_ID"),
                    rs.getInt("Violation_Code"),
                    rs.getInt("Assignment_ID"),
                    rs.getDate("Inspection_Date").toLocalDate(),
                    rs.getString("Remarks"),
                    rs.getBigDecimal("Score"),
                    rs.getString("Grade")
                );
                inspections.add(inspection);
            }
        }
        return inspections;
    }

    // READ ONE
    public static Inspection getInspectionById(int inspectionId) throws SQLException {
        String query = "SELECT * FROM Inspections WHERE Inspection_ID = ?";
        Inspection inspection = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, inspectionId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inspection = new Inspection(
                        rs.getInt("Inspection_ID"),
                        rs.getInt("Violation_Code"),
                        rs.getInt("Assignment_ID"),
                        rs.getDate("Inspection_Date").toLocalDate(),
                        rs.getString("Remarks"),
                        rs.getBigDecimal("Score"),
                        rs.getString("Grade")
                    );
                }
            }
        }
        return inspection;
    }

    // UPDATE
    public static boolean updateInspection(Inspection inspection) throws SQLException {
        String query = "UPDATE Inspections SET Violation_Code = ?, Assignment_ID = ?, Inspection_Date = ?, Remarks = ?, Score = ?, Grade = ? WHERE Inspection_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspection.getViolationCode());
            stmt.setInt(2, inspection.getAssignmentId());
            stmt.setDate(3, Date.valueOf(inspection.getInspectionDate()));
            stmt.setString(4, inspection.getRemarks());
            stmt.setBigDecimal(5, inspection.getScore());
            stmt.setString(6, inspection.getGrade());
            stmt.setInt(7, inspection.getInspectionId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // DELETE
    public static boolean deleteInspection(int inspectionId) throws SQLException {
        String query = "DELETE FROM Inspections WHERE Inspection_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, inspectionId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}