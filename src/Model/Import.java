/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Import {
    private int Import_id;
    private int Employee_id;
    private int Suplier_id;
    private float TotalPrice;


    public Import(int Employee_id, int Suplier_id, float TotalPrice) {
        this.Employee_id = Employee_id;
        this.Suplier_id = Suplier_id;
        this.TotalPrice = TotalPrice;
    }
    public Import() {
    }
    
    public int getImport_id() {
        return Import_id;
    }

    public void setImport_id(int Import_id) {
        this.Import_id = Import_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public int getSuplier_id() {
        return Suplier_id;
    }

    public void setSuplier_id(int Suplier_id) {
        this.Suplier_id = Suplier_id;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

   
}
