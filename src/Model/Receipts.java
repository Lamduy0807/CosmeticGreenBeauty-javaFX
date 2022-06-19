/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Receipts {

    private int Receipt_id;
    private int Employee_id;
    private String Content;
    private float TotalPay;
    private String Status;
    private String CreateDate;

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

    public Receipts() {
    }

    public Receipts(int Receipt_id, int Employee_id, String Content, float TotalPay, String Status, String CreateDate) {
        this.Receipt_id = Receipt_id;
        this.Employee_id = Employee_id;
        this.Content = Content;
        this.TotalPay = TotalPay;
        this.Status = Status;
        this.CreateDate = CreateDate;
    }

    public int getReceipt_id() {
        return Receipt_id;
    }

    public void setReceipt_id(int Receipt_id) {
        this.Receipt_id = Receipt_id;
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

}
