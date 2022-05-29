/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Employee {
    private int Employee_id;;
    private String EmployName;
    private String Citizen_id;
    private String Address;
    private String PhoneNumber;
    private String Email;
    private String Position;
    private String Username;
    private String Password;

    public Employee(int Employee_id, String EmployName, String Citizen_id, String Address, String PhoneNumber, String Email, String Position, String Username, String Password) {
        this.Employee_id = Employee_id;
        this.EmployName = EmployName;
        this.Citizen_id = Citizen_id;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Position = Position;
        this.Username = Username;
        this.Password = Password;
    }

    public Employee(String EmployName, String Citizen_id, String Address, String PhoneNumber, String Email, String Position, String Username, String Password) {
        this.EmployName = EmployName;
        this.Citizen_id = Citizen_id;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Position = Position;
        this.Username = Username;
        this.Password = Password;
    }
    

    public Employee() {
    }
    

    @Override
    public String toString() {
        return "Employee{" + "Employee_id=" + Employee_id + ", EmployName=" + EmployName + ", Citizen_id=" + Citizen_id + ", Address=" + Address + ", PhoneNumber=" + PhoneNumber + ", Email=" + Email + ", Position=" + Position + ", Username=" + Username + ", Password=" + Password + '}';
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public String getEmployName() {
        return EmployName;
    }

    public void setEmployName(String EmployName) {
        this.EmployName = EmployName;
    }

    public String getCitizen_id() {
        return Citizen_id;
    }

    public void setCitizen_id(String Citizen_id) {
        this.Citizen_id = Citizen_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    
    
            
    
    
}
