import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    private Connection conn;

    public ReportDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getComplianceTrendData(int year) throws Exception {
        List<Object[]> data = new ArrayList<>();
        String sql = "SELECT e.name, " +
                     "SUM(CASE WHEN i.grade = 'Pass' THEN 1 ELSE 0 END) AS passed_inspections, " +
                     "SUM(CASE WHEN i.grade = 'Fail' THEN 1 ELSE 0 END) AS failed_inspections " +
                     "FROM Establishment e " +
                     "LEFT JOIN Inspection i ON e.establishmentId = i.establishmentId AND YEAR(i.inspectionDate) = ? " +
                     "GROUP BY e.establishmentId, e.name " +
                     "ORDER BY e.name";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString("name"),
                    rs.getInt("passed_inspections"),
                    rs.getInt("failed_inspections")
                };
                data.add(row);
            }
        }
        return data;
    }
}
