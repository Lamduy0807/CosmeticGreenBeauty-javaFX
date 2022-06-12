/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.Implement.DetailExportDAOImplement;
import DAO.Implement.DetailImportDAOImplement;
import DAO.Implement.ExportDAOImplement;
import DAO.Implement.ImportDAOImplement;
import DAO.Implement.ProductDAOImplement;
import DAO.Implement.SupplierDAOImplement;
import Holder.UserHolder;
import Model.DetailExport;
import Model.DetailImport;
import Model.Export;
import Model.Import;
import Model.Product;
import Model.Supplier;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
/**
 *
 * @author Duy
 * this file is a controller for Export.fxml file
 */
public class ExportController implements Initializable, EventHandler<ActionEvent> {
     @FXML
    private TableView<DetailExport> DetailExporttb;

    @FXML
    private TableColumn<DetailExport, Integer> dpProIdCol;

    @FXML
    private TableColumn<DetailExport, String> dpProNameCol;

    @FXML
    private TableColumn<DetailExport, Float> dpExportPriceCol;

    @FXML
    private TableColumn<DetailExport, Integer> dpQuanCol;

    @FXML
    private TableColumn<DetailExport, Float> dpTotalCol;

    @FXML
    private TableView<Product> Producttb;

    @FXML
    private TableColumn<Product, Integer> pIdCol;
    @FXML
    private TableColumn<Product, String> pNameCol;
    @FXML
    private TableColumn<Product, Float> pPriceCol;
    @FXML
    private TableColumn<Product, String> pUnitCol;
    @FXML
    private TableColumn<Product, String> pDescriptionCol;
    @FXML
    private TableColumn<Product, String> pOriginalCol;
    @FXML
    private TableColumn<Product, String> pTypeCol;

    @FXML
    private JFXTextField tbProductID;

    @FXML
    private JFXTextField tbExportPrice;

    @FXML
    private JFXTextField tbProductName;

    @FXML
    private JFXTextField tbQuantities;

    @FXML
    private JFXTextField tbPrice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private Text txtTotal;

    @FXML
    private JFXTextField tbExportReason;

    @FXML
    private JFXTextField tbSearch;
    private ObservableList<DetailExport> detailexports = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        pNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        pPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        pUnitCol.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        pDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        pOriginalCol.setCellValueFactory(new PropertyValueFactory<>("Original"));
        pTypeCol.setCellValueFactory(new PropertyValueFactory<>("ProductType"));
        
        dpProIdCol.setCellValueFactory(new PropertyValueFactory<>("Product_id"));
        dpProNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        dpExportPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        dpQuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        dpTotalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));
        
        try {
            FillData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Producttb.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          if(!Bindings.isEmpty(Producttb.getItems()).get())
          {
              selectItem(newValue);
              btnAdd.setDisable(false);
                btnDelete.setDisable(true);
                btnEdit.setDisable(true);
              btnCancel.setDisable(false);
          }
            
        });
        DetailExporttb.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          selectItemExported(newValue);
          btnAdd.setDisable(true);
          btnDelete.setDisable(false);
          btnEdit.setDisable(false);
        });
        
        tbSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                //System.out.println("textfield changed from " + oldValue + " to " + newValue);
                if(!"".equals(newValue))
                {
                    ObservableList<Product> products = FXCollections.observableArrayList();
                    try {
                        products = ProductDAOImplement.getInstance().searchProduct(newValue);
                        Producttb.setItems(products);
                        Producttb.refresh();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        FillData();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
        tbQuantities.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[0-9]+$")&& !"".equals(newValue))
            {
                tbQuantities.setText(oldValue);
                Notifications.create().title("ERROR").text("Quantities must be number")
                        .showError();
            }
        });
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        btnCancel.setDisable(true);
        btnCreate.setDisable(true);
    }
    //handle Click event for each button
    @Override
    public void handle(ActionEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(event.getSource()== btnAdd)
        {
            int id = Integer.parseInt(tbProductID.getText());
            String name = tbProductName.getText();
            float exportprice = Float.parseFloat(tbExportPrice.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float price = Float.parseFloat(tbPrice.getText());
            float tem = 0;
            
            boolean isFound = false;
            for(DetailExport DT: detailexports)
            {
                if(DT.getProduct_id() == id)
                {
                    int temp = DT.getQuantity();
                    DT.setQuantity(quan+temp);
                    float temp2 = DT.getTotal();
                    tem = temp2;
                    DT.setTotal(price+temp2);
                   
                    isFound = true;
                }
            }

            if(!isFound)
            {
                DetailExport di = new DetailExport(id, 0, name, exportprice, quan, price );
                detailexports.add(di);
                DetailExporttb.setItems(detailexports);
            }
            else{
                DetailExporttb.refresh();
            }
            handelCalculateTotal();
            
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total + tem));
            btnCreate.setDisable(false);
            btnCancel.setDisable(false);
            btnAdd.setDisable(true);
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            ClearData();
        }
        else if(event.getSource()== btnEdit){
            int id = Integer.parseInt(tbProductID.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float price = Float.parseFloat(tbPrice.getText());
            float temp = 0;
            for(DetailExport DT: detailexports)
            {
                if(DT.getProduct_id() == id)
                {
                    temp = DT.getTotal();
                    DT.setQuantity(quan);
                    DT.setTotal(price);
                }
            }
            
            DetailExporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp + price));
            btnEdit.setDisable(true);
            btnDelete.setDisable(true);
            ClearData();
        }
        else if(event.getSource()==btnCancel){
            ClearData();
            tbExportReason.setText("");
            txtTotal.setText(Float.toString(0));
            detailexports.clear();
            DetailExporttb.refresh();
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            btnCancel.setDisable(true);
            btnCreate.setDisable(true);
            
        }
        else if(event.getSource()== btnDelete){
            int id = Integer.parseInt(tbProductID.getText());
            float temp = 0;
            int index = 0;
            for(DetailExport DT: detailexports)
            {
                if(DT.getProduct_id() == id)
                {
                    temp = DT.getTotal();
                    break;
                }
                index++;
            }
            detailexports.remove(index);
            DetailExporttb.refresh();
            float total = Float.parseFloat(txtTotal.getText());
            txtTotal.setText(Float.toString(total - temp));
            btnDelete.setDisable(true);
            btnEdit.setDisable(true);
            if(detailexports.isEmpty())
            {
                btnCreate.setDisable(true);
            }
            ClearData();
        }
        else if(event.getSource()== btnCreate){
            if("".equals(tbExportReason.getText()))
            {
                Notifications.create().title("ERROR").text("You must fill Export reason!!!")
                  .showError();
            }
            else
            {
            UserHolder holder = UserHolder.getInstance();
            User u = holder.getUser();
            int Id = u.getiID();
            String reason = tbExportReason.getText();
            float total = Float.parseFloat(txtTotal.getText()); 
            Export im = new Export(Id, reason, total);
            try {
                String id = ExportDAOImplement.getInstance().InsertNewExport(im);
                if(!"".equals(id))
                {
                    int ID = Integer.parseInt(id);
                    boolean flag = true;
                    for(DetailExport DT: detailexports)
                    {
                        flag = DetailExportDAOImplement.getInstance().InsertToDatabase(DT, ID);
                    }
                    System.out.print(flag? "success" : "fail");
                    if(flag)
                    {
                        Notifications.create().title("Information").text("Create successfully!!")
                        .showInformation();
                        ClearData();
                        txtTotal.setText(Float.toString(0));
                        tbExportReason.setText("");
                        detailexports.clear();
                        DetailExporttb.refresh();
                        btnAdd.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEdit.setDisable(true);
                        btnCancel.setDisable(true);
                        btnCreate.setDisable(true);
                    }
                    else{
                        Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                        .showError();
                    }
                }
                else
                {
                    Notifications.create().title("ERROR").text("There were some errors. Please check again!!")
                        .showError();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ImportController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
    //handle select a row in product table
    private void selectItem(Product product) {
        tbProductID.setText(String.valueOf(product.getProduct_id()));
        tbProductName.setText(product.getProductName());
        tbExportPrice.setText(String.valueOf(product.getPrice()));
    }
    //handle select a row in detailt table
    private void selectItemExported(DetailExport DT) {
        if(!detailexports.isEmpty())
        {
            tbProductID.setText(String.valueOf(DT.getProduct_id()));
            tbProductName.setText(DT.getProductName());
            tbExportPrice.setText(String.valueOf(DT.getPrice()));
            tbQuantities.setText(String.valueOf(DT.getQuantity()));
            tbPrice.setText(String.valueOf(DT.getTotal()));
        }
    }
    //Fill data from datbase into Product table
    private void FillData() throws ClassNotFoundException{
        ObservableList<Product> products = ProductDAOImplement.getInstance().getListOfProduct();
        Producttb.setItems(products);
    }
    //calculate price when textbox quantities changed
    public void handleCalculatePrice(){
        if(!"".equals(tbQuantities.getText()) && !"".equals(tbExportPrice.getText())) {
            float importprice = Float.parseFloat(tbExportPrice.getText());
            int quan = Integer.parseInt(tbQuantities.getText());
            float result = importprice*quan;
            tbPrice.setText(Float.toString(result));
        }
    }
    //Calculate total proce for all items added 
    public void handelCalculateTotal(){
        float total = 0;
        for(DetailExport di: detailexports){
            total = total + di.getPrice();
        }
        
        txtTotal.setText(Float.toString(total));
    }
    //Clear data
    public void ClearData(){
        tbProductID.setText("");
        tbExportPrice.setText("");
        tbProductName.setText("");
        tbQuantities.setText("");
        tbPrice.setText("");
    }
}
