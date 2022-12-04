package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.ResourceBundle;

public class PersonalStock implements Initializable {

    @FXML
    private TableView<personalStockTable> table_stocks;

    @FXML
    private TableColumn<personalStockTable, String> col_productID;

    @FXML
    private TableColumn<personalStockTable, String> col_productName;

    @FXML
    private TableColumn<personalStockTable, Integer> col_count;

    @FXML
    private TableColumn<personalStockTable, Date> col_date;

    @FXML
    private TextField search;

    @FXML
    private JFXButton submit;




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

    ObservableList<personalStockTable> listM;

    int index = -1;

    String sql = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    personalStockTable stockTable = null;

    @FXML
    void Submit(ActionEvent event) {

        conn = mysqlconnect.ConnectDb();



        if (search.getText().equals("1234")){

            String sql1 = "select * from  personalstock WHERE srID = '1234'";

            col_productID.setCellValueFactory(new PropertyValueFactory<personalStockTable, String>("productID"));
            col_productName.setCellValueFactory(new PropertyValueFactory<personalStockTable, String>("proModel"));
            col_count.setCellValueFactory(new PropertyValueFactory<personalStockTable, Integer>("count"));
            col_date.setCellValueFactory(new PropertyValueFactory<personalStockTable, Date>("date"));


            listM = mysqlconnect.getDatapersonalStockTable();
            table_stocks.setItems(listM);
        }

    }


        public void initialize(URL url, ResourceBundle rb){



            col_productID.setCellValueFactory(new PropertyValueFactory<personalStockTable, String>("productID"));
            col_productName.setCellValueFactory(new PropertyValueFactory<personalStockTable, String>("proModel"));
            col_count.setCellValueFactory(new PropertyValueFactory<personalStockTable, Integer>("count"));
            col_date.setCellValueFactory(new PropertyValueFactory<personalStockTable, Date>("date"));


            listM = mysqlconnect.getDatapersonalStockTable();
            table_stocks.setItems(listM);



        }


    public void Search_Product(ActionEvent event) {
    }
}
