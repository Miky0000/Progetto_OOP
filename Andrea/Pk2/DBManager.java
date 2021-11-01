package Pk2;

import java.sql.*;

/**
 * Class for managing DB connections making use of the singleton pattern.
 * Supports any JDBC Connection as long as the proper driver and connection
 * string are provided.
 *
 * @author Nicola Bicocchi
 */
public class DBManager {
    public static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=Mysqloop98!";
    static Connection connection;
    static Statement statement;

    public DBManager(String jdbc_driver, String jdbcUrl, int typeScrollSensitive, int concurUpdatable) throws ClassNotFoundException, SQLException{
        Class.forName(jdbc_driver);
        try {
            connection=DriverManager.getConnection(jdbcUrl);
            statement=connection.createStatement(typeScrollSensitive,concurUpdatable);
            statement.setQueryTimeout(30);
            showMetadata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void setConnection(String Driver, String URL) {
        JDBC_Driver = Driver;
        JDBC_URL = URL;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            if (JDBC_Driver == null || JDBC_URL == null) {
                throw new IllegalStateException("Illegal request. Call setConnection() before.");
            }
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL);
            showMetadata();
        }
        return connection;
    }

    public static void showMetadata() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Illegal request. Connection not established");
        }

        DatabaseMetaData md = connection.getMetaData();
        System.out.println("-- ResultSet Type --");
        System.out.println("Supports TYPE_FORWARD_ONLY: " + md.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
        System.out.println("Supports TYPE_SCROLL_INSENSITIVE: " + md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("Supports TYPE_SCROLL_SENSITIVE: " + md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
        System.out.println("-- ResultSet Concurrency --");
        System.out.println("Supports CONCUR_READ_ONLY: " + md.supportsResultSetType(ResultSet.CONCUR_READ_ONLY));
        System.out.println("Supports CONCUR_UPDATABLE: " + md.supportsResultSetType(ResultSet.CONCUR_UPDATABLE));
    }

    public ResultSet executeQuery(String query) throws SQLException{
        return statement.executeQuery(query);
    }
    public int executeUpdate(String query) throws SQLException{
        return statement.executeUpdate(query);
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
