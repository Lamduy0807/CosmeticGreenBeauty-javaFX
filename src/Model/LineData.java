/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class LineData {
    private Date lineDate;
    private float sumValue;

    public LineData(Date lineDate, float sumValue) {
        this.lineDate = lineDate;
        this.sumValue = sumValue;
    }

    public LineData() {
    }
    
    
    public Date getLineDate() {
        return lineDate;
    }

    public void setLineDate(Date lineDate) {
        this.lineDate = lineDate;
    }

    public float getSumValue() {
        return sumValue;
    }

    public void setSumValue(float sumValue) {
        this.sumValue = sumValue;
    }
    
    
}
