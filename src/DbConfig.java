import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static Connection getConnection() {
        var url = "jdbc:postgresql://localhost:5432/postgres";
        var user = "postgres";
        var password = "lydev";
        try {
            return DriverManager.getConnection(url, user, password);

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            throw new RuntimeException("Alinmadi");
        }
    }
    public static void closeConnection(Connection connection)  {
        try {
            connection.close();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

    }
}
