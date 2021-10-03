package xyz.sunnytoday.common;

import java.sql.*;

public class JDBCTemplate {

    //OJDBC driver
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    //DB Connection info
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";

    private static Connection connection;

    private JDBCTemplate() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    /*
     * BoardService에서 connection을 close 함으로 close 후에 새 연결을 얻어놓는다. (다음 사용을 위해)
     *
     * 동시성 문제가 생길 것 같습니다. 여러 사용자가 사이트를 이용시 하나의 트랜잭션 커밋전에 다른사용자의 sql이 실행되며 원자성이 깨지거나,
     * connection 사용 구현이 스레드 세이프하게 되어 있다면 한명의 사용자가 connection을 사용하고 close할때 까지 기다리던 사용자가 참조하는
     * connection은 사용시점에 이미 전 사용자에 의해 close 되어 있는 객체일 수 있을 것 같습니다.
     *
     * */
    public static void close(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
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
