/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Admin
 */
public class User {
    private String sUsername;
    private String sPassword;

    public User(String sUsername, String sPassword) {
        this.sUsername = sUsername;
        this.sPassword = sPassword;
    }

    public User() {
    }

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
    Connection con;
    public boolean CheckAccount() throws SQLException
    {
        try {
            con = DbConnection.getConnect();
        } catch (ClassNotFoundException ex) {
            System.out.print("Error DB connect");
        }
        int count = 0;
        String querry = "Select Count(*) as 'count' from Employee where Username = '" + sUsername + "' and Password = '" + sPassword + "'";
        
        ResultSet rs =con.createStatement().executeQuery(querry);
        while(rs.next()){
            count =rs.getInt("count");
        }
        if(count>0)
            return true;
        else
            return false;
    }
}
