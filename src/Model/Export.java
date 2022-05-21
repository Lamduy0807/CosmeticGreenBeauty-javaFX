/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Export {
    private int Export_id;
    private int Employee_id;
    private String ExportReason;
    private float TotalMoney;

    public Export(int Employee_id, String ExportReason, float TotalMoney) {
        this.Employee_id = Employee_id;
        this.ExportReason = ExportReason;
        this.TotalMoney = TotalMoney;
    }

    public Export() {
    }
    
    public int getExport_id() {
        return Export_id;
    }

    public void setExport_id(int Export_id) {
        this.Export_id = Export_id;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public String getExportReason() {
        return ExportReason;
    }

    public void setExportReason(String ExportReason) {
        this.ExportReason = ExportReason;
    }

    public float getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(float TotalMoney) {
        this.TotalMoney = TotalMoney;
    }
    
}
