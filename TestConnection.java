import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        
        try {
            // Using your exact class name
            Connection conn = DataBaseConnection.getConnection();
            
            if (conn != null) {
                System.out.println("✅ SUCCESS! The Java app is communicating with MySQL.");
            } else {
                System.out.println("❌ FAILED! The connection returned null.");
                System.out.println("Check the terminal for any error messages printed by your DataBaseConnection class.");
            }
        } catch (Exception e) {
            System.out.println("❌ FAILED! An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}