package InstantMessenger.srv;

import InstantMessenger.srv.AuthService;

import java.sql.*;

public class AuthServiceSQLite implements AuthService {
    private static Connection connection;

    public AuthServiceSQLite() {
        try {
            getConnection();
            createTableandInsertUsers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getConnection() throws SQLException, ClassNotFoundException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
    }

    private void createTableandInsertUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE IF NOT EXISTS users(id integer primary key autoincrement,login text not null unique,pwd text not null,nick text not null unique );");
        String query = "with t (login,pwd,nick) as " +
                "(" +
                "   values" +
                "       ('login1', 'pwd1', 'nick1')," +
                "       ('login2', 'pwd2', 'nick2')," +
                "       ('login3', 'pwd3', 'nick3')" +
                ")" +
                "insert into users" +
                "   (login,pwd,nick)" +
                "select " +
                "   t.login, t.pwd, t.nick " +
                "from " +
                "   t " +
                "where " +
                "   not exists" +
                "   (" +
                "       select" +
                "           1" +
                "       from" +
                "           users u" +
                "       where" +
                "           u.login = t.login" +
                "   )";
        statement.execute(query);
        ResultSet rs = statement.executeQuery("select * from users");
    }

    @Override
    public boolean changeNick(String oldNick, String newNick) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set nick = ? where nick = ?");
            preparedStatement.setString(1, newNick);
            preparedStatement.setString(2, oldNick);
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getNickbyLoginandPwd(String login, String pwd) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select nick from users where login = ? and pwd = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pwd);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
