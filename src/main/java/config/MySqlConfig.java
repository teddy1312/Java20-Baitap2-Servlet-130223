package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConfig {
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3302/bt_login";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1312";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);

        } catch (Exception e){
            System.out.println("An error occurred when connecting to database " +e.getMessage() );
            e.printStackTrace();
        }

        return connection;
    }
}
