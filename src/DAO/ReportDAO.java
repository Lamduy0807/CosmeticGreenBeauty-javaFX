/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.LineData;
import Model.TopProduct;
import javafx.collections.ObservableList;

/**
 *
 * @author Duy
 * DAO interface for Report
 */
public interface ReportDAO {
    ObservableList<TopProduct> getTop10Product();
    String getProductMonth(String month, String year);
    String getProductDay(String day,String month, String year);
    String getBillMonth(String month, String year);
    String getBillDay(String day,String month, String year);
    String getRevenueMonth(String month, String year);
    String getRevenueDay(String day,String month, String year);
    ObservableList<LineData> getLineData(String month, String year);
}
