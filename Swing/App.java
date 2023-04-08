package Swing;

import Database.SQLiteDB;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.SQLiteDB.con;

public class App {
    public static void main(String[] args) throws SQLException {
        new Login();
        //new Dashboard();
        //new Register();

        //Database
        SQLiteDB DB = new SQLiteDB();
        ResultSet res = null;
        try{
            res = DB.displayUsers();
            while(res.next()) {
                System.out.println(res.getString("Uname") + res.getString("Email"));
            }
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
