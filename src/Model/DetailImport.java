/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class DetailImport {
    private int Product_id;
    private int Import_id;
    private String ProductName;
    private float ImportPrice;
    private int Quantities;
    private float Total;

    public DetailImport(int Product_id,  String ProductName, int Import_id, float ImportPrice, int Quantities, float Total) {
        this.Product_id = Product_id;
        this.Import_id = Import_id;
        this.ProductName = ProductName;
        this.ImportPrice = ImportPrice;
        this.Quantities = Quantities;
        this.Total = Total;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int Product_id) {
        this.Product_id = Product_id;
    }

    public int getImport_id() {
        return Import_id;
    }

    public void setImport_id(int Import_id) {
        this.Import_id = Import_id;
    }

    public float getImportPrice() {
        return ImportPrice;
    }

    public void setImportPrice(float ImportPrice) {
        this.ImportPrice = ImportPrice;
    }

    public int getQuantities() {
        return Quantities;
    }

    public void setQuantities(int Quantities) {
        this.Quantities = Quantities;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
}
