import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String query = "SELECT City_name FROM REF_Cities";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                City city = new City(rs.getString("City_name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public void addCity(City city) {
        String query = "INSERT INTO REF_Cities (City_name) VALUES (?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, city.getCityName());
            
            stmt.executeUpdate();
            System.out.println("City added successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding city: " + e.getMessage());
        }
    }
}