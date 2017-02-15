package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sun on 2/15/17.
 */
public interface DbConnection {

    Connection getConnection(String dbHost, String username, String password, String dbName) throws SQLException, ClassNotFoundException;

    boolean executeUpdateQuery(Connection connection, String queryString) throws SQLException;

    ResultSet executeSelectQuery(Connection connection, String queryString) throws SQLException;

    void closeConnection(Connection connection) throws SQLException;
}
