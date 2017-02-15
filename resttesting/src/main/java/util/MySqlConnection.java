package util;

import models.DbConnection;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by sun on 2/15/17.
 */
public class MySqlConnection implements DbConnection {
    final static Logger logger = Logger.getLogger(DbConnection.class);

    @Override
    public Connection getConnection(String dbHost, String username, String password, String dbName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(
                "jdbc:mysql://"+ dbHost +":3306/"+ dbName + "?autoReconnect=true&useSSL=false", username, password);
        return connection;
    }

    @Override
    public boolean executeUpdateQuery(Connection connection, String queryString) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(queryString);
    }

    @Override
    public ResultSet executeSelectQuery(Connection connection, String queryString) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(queryString);
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
