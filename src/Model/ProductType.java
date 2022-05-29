/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class ProductType {

    private int ProductType_id;
    private String TypeName;

    public ProductType() {
    }

    public ProductType(int ProductType_id, String TypeName) {
        this.ProductType_id = ProductType_id;
        this.TypeName = TypeName;
    }

    public ProductType(String TypeName) {
        this.TypeName = TypeName;
    }
    

    @Override
    public String toString() {
        return "ProductType{" + "ProductType_id=" + ProductType_id + ", TypeName=" + TypeName + '}';
    }

    public int getProductType_id() {
        return ProductType_id;
    }

    public void setProductType_id(int ProductType_id) {
        this.ProductType_id = ProductType_id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

}
