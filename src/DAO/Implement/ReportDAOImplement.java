/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Implement;

import DAO.ReportDAO;
import Db.DbConnection;
import Model.LineData;
import Model.TopProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Duy
 * this file is an implementation for ReportDAO file
 */
public class ReportDAOImplement implements ReportDAO{

    private static ReportDAO instance;
   private Connection connection;
   private static final Logger LOG = Logger.getLogger(ReportDAO.class.getName());
   private ReportDAOImplement() throws ClassNotFoundException{
       connection = DbConnection.getConnect();
   }
   public static ReportDAO getInstance() throws ClassNotFoundException{
       if(instance == null)
       {
           instance = new ReportDAOImplement();
       }
       return instance;
   }
   //Get top 10 Product by Order from database
    @Override
    public ObservableList<TopProduct> getTop10Product() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT TOP 10 WITH TIES ProductName , sum(Quantity) as Sold from Product, DetailBill WHERE DetailBill.Product_id = Product.Product_id GROUP BY ProductName ORDER BY SUM(Quantity) DESC";
        ObservableList<TopProduct> topproducts = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    String name = resultSet.getString(1);
                    int quan = resultSet.getInt(2);
                    TopProduct toppro = new TopProduct(name, quan);
                    topproducts.add(toppro);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return topproducts;
    }
    //Get product sold in a month
    @Override
    public String getProductMonth(String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT SUM(DetailBill.Quantity) AS SL FROM Bill, DetailBill WHERE Bill.Bill_id = DetailBill.Bill_id and MONTH(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    quan = resultSet.getInt(1);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //Get product sold in a day
    @Override
    public String getProductDay(String day, String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT SUM(DetailBill.Quantity) AS SL FROM Bill, DetailBill WHERE Bill.Bill_id = DetailBill.Bill_id and DAY(DateBill) = '"+day +"' AND MONTH(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    if(resultSet.getObject("SL") != null)
                    {
                        quan = resultSet.getInt(1);
                    }
               
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //get bill created in a month
    @Override
    public String getBillMonth(String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT COUNT(Bill_id) as SL from Bill Where Month(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    quan = resultSet.getInt(1);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //get bill created in a day
    @Override
    public String getBillDay(String day, String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT COUNT(Bill_id) as SL from Bill Where Day(DateBill) = '" + day + "' and Month(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    quan = resultSet.getInt(1);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //get revenue in a month
    @Override
    public String getRevenueMonth(String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT SUM(BillValue) as Tong from Bill Where Month(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    quan = resultSet.getInt(1);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //get revenue in a day
    @Override
    public String getRevenueDay(String day, String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT SUM(BillValue) as Tong from Bill Where Day(DateBill) = '" + day + "' and Month(DateBill) = '"+ month +"' AND YEAR(DateBill) = '"+year+"'";
        int quan = 0;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    quan = resultSet.getInt(1);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return String.valueOf(quan);
    }
    //Get data for Line Chart from database
    @Override
    public ObservableList<LineData> getLineData(String month, String year) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "Select CAST (DateBill AS DATE) AS Ngay, sum(BillValue) as Tong from Bill where MONTH(DateBill) = '"+ month +"' and Year(DateBill) = '"+ year+"' group by CAST (DateBill AS DATE) order by CAST (DateBill AS DATE)";
        ObservableList<LineData> linedata = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Date date = resultSet.getDate(1);
                    float sum = resultSet.getFloat(2);
                    LineData ld = new LineData(date, sum);
                    linedata.add(ld);
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return linedata;
    }
    
}
