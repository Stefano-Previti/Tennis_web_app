package tenniswebapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
public class JDBCconnection {

    private JDBCconnection() {
    }

	        public static Connection getConnection() throws Exception  {
            ResourceBundle rb = ResourceBundle.getBundle("tenniswebapp.System");
            String url=rb.getString("url");
            String username=rb.getString("username");
            String password=rb.getString("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
    } 
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        } 
    }

    
}

