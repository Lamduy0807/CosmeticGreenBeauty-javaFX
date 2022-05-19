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
    private String sPosition;
    private int iID;
    private String sName;
    private String sCitizenID;
    private String sAddress;
    private String sPhone;
    private String sEmail;

    public User(String sUsername, String sPassword, String sPosition, int iID, String sName, String sCitizenID, String sAddress, String sPhone, String sEmail) {
        this.sUsername = sUsername;
        this.sPassword = sPassword;
        this.sPosition = sPosition;
        this.iID = iID;
        this.sName = sName;
        this.sCitizenID = sCitizenID;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
        this.sEmail = sEmail;
    }
    
    
    
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

    public String getsPosition() {
        return sPosition;
    }

    public void setsPosition(String sPosition) {
        this.sPosition = sPosition;
    }

    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsCitizenID() {
        return sCitizenID;
    }

    public void setsCitizenID(String sCitizenID) {
        this.sCitizenID = sCitizenID;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
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
