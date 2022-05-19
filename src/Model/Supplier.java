/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Supplier {
    public int Suplier_Id;
    public String SuplierName;
    public String Email;
    public String PhoneNumber;
    public String Address;

    public Supplier(int Suplier_Id, String SuplierName, String Email, String PhoneNumber, String Address) {
        this.Suplier_Id = Suplier_Id;
        this.SuplierName = SuplierName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
    }

    public Supplier(String SuplierName, String Email, String PhoneNumber, String Address) {
        this.SuplierName = SuplierName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
    }
    
    public Supplier() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getSuplier_Id() {
        return Suplier_Id;
    }

    public void setSuplier_Id(int Suplier_Id) {
        this.Suplier_Id = Suplier_Id;
    }

    public String getSuplierName() {
        return SuplierName;
    }

    public void setSuplierName(String SuplierName) {
        this.SuplierName = SuplierName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
}
