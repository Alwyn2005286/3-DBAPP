import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentComplianceTrendReportDAO {

    public List<String[]> getComplianceTrend(int year) {
        List<String[]> trendData = new ArrayList<>();
        String sql = "SELECT e.establishment_name, " +
                     "SUM(CASE WHEN i.grade = 'PASS' THEN 1 ELSE 0 END) AS passed_inspections, " +
                     "SUM(CASE WHEN i.grade = 'FAIL' THEN 1 ELSE 0 END) AS failed_inspections " +
                     "FROM Establishments e " +
                     "JOIN Inspections i ON e.establishment_id = i.establishment_id " +
                     "WHERE YEAR(i.inspection_date) = ? " +
                     "GROUP BY e.establishment_name " +
                     "ORDER BY e.establishment_name";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] row = new String[3];
                row[0] = rs.getString("establishment_name");
                row[1] = rs.getString("passed_inspections");
                row[2] = rs.getString("failed_inspections");
                trendData.add(row);
                System.out.println("Fetched data: " + String.join(", ", row)); // Log fetched data
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return trendData;
    }
}
