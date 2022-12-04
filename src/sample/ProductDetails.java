package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProductDetails implements Initializable {

    @FXML
    private TableView<ProductDettailsTable> table_productDetails;

    @FXML
    private TableColumn<ProductDettailsTable, String> col_productId;

    @FXML
    private TableColumn<ProductDettailsTable, String> col_productName;

    @FXML
    private TableColumn<ProductDettailsTable, String> col_model;

    @FXML
    private TableColumn<ProductDettailsTable, String> col_color;

    @FXML
    private TableColumn<ProductDettailsTable, Float> col_unitPrice;

    @FXML
    private TextField tf_search;

    @FXML
    private JFXButton Search;



    private Stage stage;
    private Scene scene;
    private Object root;


    public void PersonalStocks(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PersonalStock.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void DailyReport(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("DailyReport.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void ProductDetails(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ProductDetails.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<ProductDettailsTable> listM;
    ObservableList<ProductDettailsTable> dataList;

    int index = -1;

    String sql = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    personalStockTable stockTable = null;

//    public void search_product(ActionEvent event) {
//
//        col_productId.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("productID"));
//        col_productName.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proName"));
//        col_model.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proModel"));
//        col_color.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proColor"));
//        col_unitPrice.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, Float>("proPrice"));
//
//        dataList = mysqlconnect.getDataProductDettailsTable();
//        table_productDetails.setItems(dataList);
//        FilteredList<ProductDettailsTable> filteredData = new FilteredList<>(dataList, b -> true);
//        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(productdetails -> {
//                if (newValue == null || newValue.isEmpty){
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//            });
//
//        })
//
//
//    }


    public void initialize(URL url, ResourceBundle rb){

        col_productId.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("productID"));
        col_productName.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proName"));
        col_model.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proModel"));
        col_color.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, String>("proColor"));
        col_unitPrice.setCellValueFactory(new PropertyValueFactory<ProductDettailsTable, Float>("proPrice"));


        listM = mysqlconnect.getDataProductDettailsTable();
        table_productDetails.setItems(listM);



    }



}
