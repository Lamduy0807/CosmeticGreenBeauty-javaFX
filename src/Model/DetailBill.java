/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class DetailBill {
    private int Bill_id;
    private int Product_id;
    private String productName;
    private int Quantity;
    private float PresentPrice;

    public DetailBill() {
    }

    public DetailBill(int Bill_id, int Product_id, String productName, int Quantity, float PresentPrice) {
        this.Bill_id = Bill_id;
        this.Product_id = Product_id;
        this.productName = productName;
        this.Quantity = Quantity;
        this.PresentPrice = PresentPrice;
    }

    public DetailBill(int Product_id, String productName, int Quantity, float PresentPrice) {
        this.Product_id = Product_id;
        this.productName = productName;
        this.Quantity = Quantity;
        this.PresentPrice = PresentPrice;
    }
    

    @Override
    public String toString() {
        return "DetailBill{" + "Bill_id=" + Bill_id + ", Product_id=" + Product_id + ", productName=" + productName + ", Quantity=" + Quantity + ", PresentPrice=" + PresentPrice + '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

   

    public int getBill_id() {
        return Bill_id;
    }

    public void setBill_id(int Bill_id) {
        this.Bill_id = Bill_id;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int Product_id) {
        this.Product_id = Product_id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public float getPresentPrice() {
        return PresentPrice;
    }

    public void setPresentPrice(float PresentPrice) {
        this.PresentPrice = PresentPrice;
    }
    
    
    
}
