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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class DailyReport implements Initializable {

    @FXML
    private JFXButton sideDailyReport;

    @FXML
    private JFXButton sideManageStock;

    @FXML
    private JFXButton sideProductDetails;

    @FXML
    private TableView<dailyReportTable> table_report;

    @FXML
    private TableColumn<dailyReportTable, String> col_productID;

    @FXML
    private TableColumn<dailyReportTable, String> col_model;

    @FXML
    private TableColumn<dailyReportTable, Integer> col_count;

    @FXML
    private TableColumn<dailyReportTable, Float> col_price;

    @FXML
    private TableColumn<dailyReportTable, Float> col_subtotal;

    @FXML
    private TableColumn<dailyReportTable, Date> col_date;

    @FXML
    private TextField tf_ProductID;

    @FXML
    private TextField tf_count;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField tf_price;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_modify;

    @FXML
    private TextField sr;

    @FXML
    private TextField tf_toal;

    @FXML
    private TextField tf_model;

    @FXML
    private ComboBox<String> status;




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


    ObservableList<dailyReportTable> listM;

    int index = -1;


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;




    public int AddDetails () {

        conn = mysqlconnect.ConnectDb();
        String sql = "insert into personalstock (srID, productID, count, subTotal, date) values('1234', ?,?,0, ?)";

        if(tf_ProductID.getText().equals("")|| tf_count.getText().equals("")){

            JOptionPane.showMessageDialog(null, "please enter all data");

        }

        else{

            try{
                pst = conn.prepareStatement(sql);

                int a = 0;
                String state = status.getSelectionModel().getSelectedItem().toString();
                if(state == "out"){
                    Integer c=Integer.parseInt(tf_count.getText().trim());
                    a=c-2*c;
                }
                else {
                    return a;
                }

                pst.setString(1, tf_ProductID.getText().trim());

                pst.setString(2, String.valueOf(a));


                pst.setString(3, String.valueOf(datepicker.getValue()));


                pst.execute();

                col_productID.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("productID"));
                col_model.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("proModel"));
                col_count.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Integer>("count"));
                col_price.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("proPrice"));
                col_subtotal.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("subTotal"));
                col_date.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Date>("date"));

                listM = mysqlconnect.getDatadailyReportTable();
                table_report.setItems(listM);

                JOptionPane.showMessageDialog(null, "Product Add succesfully.");

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

            tf_ProductID.setText("");
            tf_count.setText("");


        }

        return 0;
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {

        index = table_report.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        tf_ProductID.setText(col_productID.getCellData(index).toString());
        tf_count.setText(col_count.getCellData(index).toString());

    }


    public int Edit() {

        try{
            conn = mysqlconnect.ConnectDb();
            String value1 = tf_ProductID.getText();
            String value2 = tf_count.getText();


            String sql = "update personalstock set productID= '"+value1+"', count= '"+value2+"' where productID= '"+value1+"' ";

            pst = conn.prepareStatement(sql);
            pst.execute();



           col_productID.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("productID"));
            col_model.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("proModel"));
           col_count.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Integer>("count"));
            col_price.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("proPrice"));
            col_subtotal.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("subTotal"));
            col_date.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Date>("date"));

            listM = mysqlconnect.getDatadailyReportTable();
            table_report.setItems(listM);

            JOptionPane.showMessageDialog(null, "Product update succesfully.");

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);

        }

        tf_ProductID.setText("");
        tf_count.setText("");

        return 0;
    }



    public void initialize(URL url, ResourceBundle rb){

        status.getItems().addAll("in","out");

        col_productID.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("productID"));
        col_model.setCellValueFactory(new PropertyValueFactory<dailyReportTable, String>("proModel"));
        col_count.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Integer>("count"));
        col_price.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("proPrice"));
        col_subtotal.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Float>("subTotal"));
        col_date.setCellValueFactory(new PropertyValueFactory<dailyReportTable, Date>("date"));

       listM = mysqlconnect.getDatadailyReportTable();
       table_report.setItems(listM);



    }

    public void myfunction(String user){

    }


}
