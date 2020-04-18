package DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
    
    private static Connection connect;
    
    public static Connection DBConnectionmethod() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        connect=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaresystem","root","");
        return connect;
    }
}