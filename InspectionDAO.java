import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InspectionDAO {
    public List<Inspection> getAllInspections() {
        List<Inspection> list = new ArrayList<>();
        String query = "SELECT inspection_id, establishment_id, inspection_date, score, grade, remarks FROM Inspections";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Inspection(
                    rs.getInt("inspection_id"),
                    rs.getInt("establishment_id"),
                    rs.getDate("inspection_date"),
                    rs.getDouble("score"),
                    rs.getString("grade"),
                    rs.getString("remarks")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public void addInspection(Inspection i) {
    String query = "INSERT INTO Inspections (establishment_id, inspection_date, score, grade, remarks) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DataBaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, i.getEstablishmentId());
        stmt.setDate(2, i.getInspectionDate());
        stmt.setDouble(3, i.getScore());
        stmt.setString(4, i.getGrade());
        stmt.setString(5, i.getRemarks());
        stmt.executeUpdate();
    } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Integer> getAvailableEstablishmentIds() {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT establishment_id FROM Establishments";
        try (Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) ids.add(rs.getInt("establishment_id"));
        } catch (Exception e) { e.printStackTrace(); }
        return ids;
    }
}