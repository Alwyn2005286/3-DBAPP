
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDAO {

    // CREATE
    public static int addEstablishment(Establishment est) throws SQLException {
        String query = "INSERT INTO establishment (Establishment_Name, Owner_Name, Address, Contact_Info, Operating_Status) " +
                       "VALUES (?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, est.getEstablishmentName());
        stmt.setString(2, est.getOwnerName());
        stmt.setString(3, est.getAddress());
        stmt.setString(4, est.getContactInfo());
        stmt.setString(5, est.getOperatingStatus());

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
    public static List<Establishment> getAllEstablishments() throws SQLException {
        List<Establishment> list = new ArrayList<>();
        String query = "SELECT * FROM establishment";

        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Establishment est = new Establishment(
                    rs.getInt("Establishment_Id"),
                    rs.getString("Establishment_Name"),
                    rs.getString("Owner_Name"),
                    rs.getString("Address"),
                    rs.getString("Contact_Info"),
                    rs.getString("Operating_Status")
            );
            list.add(est);
        }

        rs.close();
        stmt.close();
        conn.close();

        return list;
    }

    // UPDATE
    public static boolean updateEstablishment(Establishment est) throws SQLException {
        String query = "UPDATE establishment SET Establishment_Name = ?, Owner_Name = ?, Address = ?, Contact_Info = ?, Operating_Status = ? " +
                       "WHERE Establishment_Id = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, est.getEstablishmentName());
        stmt.setString(2, est.getOwnerName());
        stmt.setString(3, est.getAddress());
        stmt.setString(4, est.getContactInfo());
        stmt.setString(5, est.getOperatingStatus());
        stmt.setInt(6, est.getEstablishmentId());

        int rowsAffected = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rowsAffected > 0;
    }

    // DELETE
    public static boolean deleteEstablishment(int id) throws SQLException {
        String query = "DELETE FROM establishment WHERE Establishment_Id = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);

        int rowsAffected = stmt.executeUpdate();

        stmt.close();
        conn.close();

        return rowsAffected > 0;
    }
}
