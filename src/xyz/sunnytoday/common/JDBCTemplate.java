package xyz.sunnytoday.common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class JDBCTemplate {
    //Connection pool
    private static DataSource dataSource;

    private JDBCTemplate() {
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            if (dataSource == null) {
                Context context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/oracle.xe");
            }
            connection = dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
