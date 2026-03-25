import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDAO {

    // CREATE
    public static boolean addEstablishment(Establishment establishment) throws SQLException {
        String query = "INSERT INTO Establishments (Establishment_ID, Owner_ID, Establishment_Name, City_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, establishment.getEstablishmentId());
            stmt.setInt(2, establishment.getOwnerId());
            stmt.setString(3, establishment.getEstablishmentName());
            stmt.setString(4, establishment.getCityName());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // READ ALL
    public static List<Establishment> getAllEstablishments() throws SQLException {
        List<Establishment> establishments = new ArrayList<>();
        String query = "SELECT * FROM Establishments ORDER BY Establishment_ID DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Establishment e = new Establishment(
                    rs.getInt("Establishment_ID"),
                    rs.getInt("Owner_ID"),
                    rs.getString("Establishment_Name"),
                    rs.getString("City_name")
                );
                establishments.add(e);
            }
        }
        return establishments;
    }
    
    // READ ONE
    public static Establishment getEstablishmentById(int establishmentId) throws SQLException {
        String query = "SELECT * FROM Establishments WHERE Establishment_ID = ?";
        Establishment establishment = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, establishmentId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    establishment = new Establishment(
                        rs.getInt("Establishment_ID"),
                        rs.getInt("Owner_ID"),
                        rs.getString("Establishment_Name"),
                        rs.getString("City_name")
                    );
                }
            }
        }
        return establishment;
    }

    // UPDATE
    public static boolean updateEstablishment(Establishment establishment) throws SQLException {
        String query = "UPDATE Establishments SET Owner_ID = ?, Establishment_Name = ?, City_name = ? WHERE Establishment_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, establishment.getOwnerId());
            stmt.setString(2, establishment.getEstablishmentName());
            stmt.setString(3, establishment.getCityName());
            stmt.setInt(4, establishment.getEstablishmentId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // DELETE
    public static boolean deleteEstablishment(int establishmentId) throws SQLException {
        String query = "DELETE FROM Establishments WHERE Establishment_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, establishmentId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
