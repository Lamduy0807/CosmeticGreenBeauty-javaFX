/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Implement.ProductDAOImplement;
import DAO.Implement.ReportDAOImplement;
import Model.LineData;
import Model.Product;
import Model.TopProduct;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 *
 * @author Duy
 * this file is a controller for Report.fxml in View folder
 */
public class ReportController implements Initializable, EventHandler<ActionEvent>{
    
    @FXML
    private Text txtBillMonth;

    @FXML
    private Text txtBillDay;

    @FXML
    private Text txtProductMonth;

    @FXML
    private Text txtProductDay;

    @FXML
    private Text txtRevenueMonth;

    @FXML
    private Text txtRevenueDay;

    @FXML
    private DatePicker  datePicker;

    @FXML
    private TableView<TopProduct> Top10tb;

    @FXML
    private TableColumn<TopProduct, String> colProductName;

    @FXML
    private TableColumn<TopProduct, Integer> colSold;

    @FXML
    private LineChart<Number, Number> chartMonth;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set value for Top10tb
        colProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colSold.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        
        try {
            //Fill data into Top10tb
            FillData();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LocalDate l = NowDate();
        datePicker.setValue(l);
        //Get Time now
        try {
            //Set Data into Line Chart
            setData(l);
            addValueToLineChart(String.valueOf(l.getMonthValue()), String.valueOf(l.getYear()));
            //addValueToLineChart("12","2021");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Handle when DatePicker change
        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            try {
                //Set Data into Line Chart with the date
                setData(newValue);
                chartMonth.getData().clear();
                chartMonth.getData().removeAll();
                addValueToLineChart(String.valueOf(newValue.getMonthValue()), String.valueOf(newValue.getYear()));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    // Add value to the chart by month and year
    private void addValueToLineChart(String month, String year) throws ClassNotFoundException{
        ObservableList<LineData> linedatas = ReportDAOImplement.getInstance().getLineData(month, year);
        
        XYChart.Series seri = new XYChart.Series();
        seri.setName("REVENUE IN " + month +"/" + year);
        for(LineData ld : linedatas)
        {
            Date date = ld.getLineDate();
            seri.getData().add(new XYChart.Data(date.toString(), ld.getSumValue()));
        }
        chartMonth.getData().addAll(seri);
    }
    //Fill data to Top10tb (Top 10 products table)
    private void FillData() throws ClassNotFoundException{
        ObservableList<TopProduct> topproducts = ReportDAOImplement.getInstance().getTop10Product();
        Top10tb.setItems(topproducts);
    }
    //set Data for callendar
    private void setData(LocalDate ld) throws ClassNotFoundException{
        String day = String.valueOf(ld.getDayOfMonth());
        String month = String.valueOf(ld.getMonthValue());
        String year = String.valueOf(ld.getYear());
        
        String billm = ReportDAOImplement.getInstance().getBillMonth(month, year);
        String billd =ReportDAOImplement.getInstance().getBillDay(day, month, year);
        String productm = ReportDAOImplement.getInstance().getProductMonth(month, year);
        String productd = ReportDAOImplement.getInstance().getProductDay(day, month, year);
        String rem = ReportDAOImplement.getInstance().getRevenueMonth(month, year);
        String red = ReportDAOImplement.getInstance().getRevenueDay(day, month, year);
        
        txtBillMonth.setText(String.valueOf(billm));
        txtBillDay.setText(String.valueOf(billd));
        txtProductMonth.setText(String.valueOf(productm));
        txtProductDay.setText(String.valueOf(productd));   
        txtRevenueMonth.setText(String.valueOf(rem));
        txtRevenueDay.setText(String.valueOf(red));
    }
    @Override
    public void handle(ActionEvent t) {
        
    }
    //get localdate
    public static final LocalDate NowDate(){
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate l = LocalDate.parse(date, formatter);
        return l;
    }
}
