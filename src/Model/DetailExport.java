/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class DetailExport {
    private int Product_id;
    private int Export_id;
    private String ProductName;
    private float Price;
    private int Quantity;
    private float Total;

    public DetailExport(int Product_id, int Export_id, String ProductName, float Price, int Quantity, float Total) {
        this.Product_id = Product_id;
        this.Export_id = Export_id;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Total = Total;
    }

    public DetailExport() {
    }
            
    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int Product_id) {
        this.Product_id = Product_id;
    }

    public int getExport_id() {
        return Export_id;
    }

    public void setExport_id(int Export_id) {
        this.Export_id = Export_id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
    
    
}
