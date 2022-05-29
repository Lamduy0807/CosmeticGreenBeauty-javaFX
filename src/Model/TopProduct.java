/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class TopProduct {
    private String ProductName;
    private int Quantities;

    public TopProduct(String ProductName, int Quantities) {
        this.ProductName = ProductName;
        this.Quantities = Quantities;
    }

    public TopProduct() {
    }
    
    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getQuantities() {
        return Quantities;
    }

    public void setQuantities(int Quantities) {
        this.Quantities = Quantities;
    }
    
}
