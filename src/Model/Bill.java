/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Bill {

    private int Bill_id;
    private int Employee_id;
    private String Cus_Name;
    private String PhoneNumber;
    private float BillValue;
    private String DateBill;

    public Bill() {
    }

    public Bill(int Bill_id, int Employee_id, String Cus_Name, String PhoneNumber, float BillValue, String DateBill) {
        this.Bill_id = Bill_id;
        this.Employee_id = Employee_id;
        this.Cus_Name = Cus_Name;
        this.PhoneNumber = PhoneNumber;
        this.BillValue = BillValue;
        this.DateBill = DateBill;
    }

    public Bill(int Employee_id, String Cus_Name, String PhoneNumber, float BillValue, String DateBill) {
        this.Employee_id = Employee_id;
        this.Cus_Name = Cus_Name;
        this.PhoneNumber = PhoneNumber;
        this.BillValue = BillValue;
        this.DateBill = DateBill;
    }

    public Bill(int Employee_id, String Cus_Name, String PhoneNumber, float BillValue) {
        this.Employee_id = Employee_id;
        this.Cus_Name = Cus_Name;
        this.PhoneNumber = PhoneNumber;
        this.BillValue = BillValue;

    }

    @Override
    public String toString() {
        return "Bill{" + "Bill_id=" + Bill_id + ", Employee_id=" + Employee_id + ", Cus_Name=" + Cus_Name + ", PhoneNumber=" + PhoneNumber + ", BillValue=" + BillValue + ", DateBill=" + DateBill + '}';
    }

    public int getBill_id() {
        return Bill_id;
    }

    public void setBill_id(int Bill_id) {
        this.Bill_id = Bill_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public String getCus_Name() {
        return Cus_Name;
    }

    public void setCus_Name(String Cus_Name) {
        this.Cus_Name = Cus_Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public float getBillValue() {
        return BillValue;
    }

    public void setBillValue(float BillValue) {
        this.BillValue = BillValue;
    }

    public String getDateBill() {
        return DateBill;
    }

    public void setDateBill(String DateBill) {
        this.DateBill = DateBill;
    }

}
