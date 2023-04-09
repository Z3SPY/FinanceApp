package Database;

import java.sql.*;

public class SQLiteDB {

    public static Connection con;
    private static boolean hasData = false;

    public ResultSet displayUsers() throws SQLException, ClassNotFoundException {
        if(con == null){
            getConnection();
        }

        Statement state = con.createStatement();
        return state.executeQuery("Select Uname, email From user");
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteDB.db");
        initialise();
    }

    private void initialise() throws SQLException {
        if(!hasData){
            hasData = true;

            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("Select name from sqlite_master where type ='table' And name = 'user' ");
            if(!res.next()){
                System.out.println("Building the user Table");
                Statement state2 = con.createStatement();
                state2.executeUpdate("Create Table user(id integer, Uname varchar(60), email varchar(60), pass varchar(60), primary key(id));");

                PreparedStatement prep = con.prepareStatement("Insert into user values(?,?,?,?);");
                prep.setString(2, "Clyde");
                prep.setString(3,"clyde@gmail.com");
                prep.setString(4, "12345678");
                prep.execute();
            }
        }
    }
    public void addUser(String uName, String email, String pass) throws SQLException, ClassNotFoundException {
        if(con == null){
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("Insert into user values(?,?,?,?);");
        prep.setString(2, uName);
        prep.setString(3, email);
        prep.setString(4, pass);
        prep.execute();
    }

    public boolean isUserExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE Uname = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        result.next();
        int count = result.getInt(1);
        return count > 0;
    }

    public boolean isUserValid(String username, String password) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE uname = ? AND pass = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        result.next();
        int count = result.getInt(1);
        return count > 0;
    }

    public profile getProfile(String username, String pass) throws SQLException {
        String sql = "SELECT uname, email FROM user WHERE uname = ? AND pass = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, pass);
        ResultSet result = statement.executeQuery();
        String retrievedUser = result.getString("uname");
        String retrievedEmail = result.getString("email");


        return new profile(retrievedEmail, retrievedUser);
    }

}

