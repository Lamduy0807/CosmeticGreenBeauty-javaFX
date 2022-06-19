/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Payslip {
    private int Payslip_id;
    private int Employee_id;
    private String Content;
    private float TotalPay;
    private String Status;
    private String CreateDate;

    public Payslip(int Payslip_id, int Employee_id, String Content, float TotalPay, String Status, String CreateDate) {
        this.Payslip_id = Payslip_id;
        this.Employee_id = Employee_id;
        this.Content = Content;
        this.TotalPay = TotalPay;
        this.Status = Status;
        this.CreateDate = CreateDate;
    }

    public Payslip() {
    }

    public int getPayslip_id() {
        return Payslip_id;
    }

    public void setPayslip_id(int Payslip_id) {
        this.Payslip_id = Payslip_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public float getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(float TotalPay) {
        this.TotalPay = TotalPay;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    
}
