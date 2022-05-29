/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Product {

    private int Product_id;
    private String ProductName;
    private float Price;
    private String Unit;
    private String Description;
    private String Original;
    private int Quantities;
    private String ProductType;

    public Product(int Product_id, String ProductName, float Price, String Unit, String Description, String Original, int Quantities, String ProductType) {
        this.Product_id = Product_id;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Unit = Unit;
        this.Description = Description;
        this.Original = Original;
        this.Quantities=  Quantities;
        this.ProductType = ProductType;
    }

    public Product(String ProductName, float Price, String Unit, String Description, String Original, String ProductType) {
        this.ProductName = ProductName;
        this.Price = Price;
        this.Unit = Unit;
        this.Description = Description;
        this.Original = Original;
        this.ProductType = ProductType;
    }

    public Product(int Product_id, String ProductName, float Price, String Unit, String Description, String Original, String ProductType) {
        this.Product_id = Product_id;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Unit = Unit;
        this.Description = Description;
        this.Original = Original;
        this.ProductType = ProductType;
    }
    
    

    public int getQuantities() {
        return Quantities;
    }

    public void setQuantities(int Quantities) {
        this.Quantities = Quantities;
    }

    public Product(String ProductName, float Price, String Unit, String Description, String Original, int Quantities, String ProductType) {
        this.ProductName = ProductName;
        this.Price = Price;
        this.Unit = Unit;
        this.Description = Description;
        this.Original = Original;
        this.Quantities=  Quantities;
        this.ProductType = ProductType;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" + "Product_id=" + Product_id + ", ProductName=" + ProductName + ", Price=" + Price + ", Unit=" + Unit + ", Description=" + Description + ", Original=" + Original + ", ProductType=" + ProductType + '}';
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int Product_id) {
        this.Product_id = Product_id;
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

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getOriginal() {
        return Original;
    }

    public void setOriginal(String Original) {
        this.Original = Original;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

}
