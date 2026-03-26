// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/DatabaseSystem";
   private static final String USER = "root";
   private static final String PASSWORD = "12345678";

   public DataBaseConnection() {
   }

   public static Connection getConnection() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/DatabaseSystem", "root", "12345678");
      } catch (ClassNotFoundException | SQLException var1) {
         System.out.println("Failed to connect to database");
         System.out.println("Error: " + ((Exception)var1).getMessage());
         ((Exception)var1).printStackTrace();
         return null;
      }
   }
}
