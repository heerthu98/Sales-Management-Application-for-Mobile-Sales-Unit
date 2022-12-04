package sample;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Manage_Products implements Initializable {
    @FXML
    private TextField tf_ProductID;

    @FXML
    private TextField tf_ProductName;

    @FXML
    private TextField tf_ProductPrice;

    @FXML
    private TableView<ManageProductsTable> Manage_Product;

    @FXML
    private TableColumn<ManageProductsTable, String> col_ProductID;

    @FXML
    private TableColumn<ManageProductsTable, String> Col_ProductName;

    @FXML
    private TableColumn<ManageProductsTable, String> col_ProductModel;

    @FXML
    private TableColumn<ManageProductsTable, String> col_ProductColor;

    @FXML
    private TableColumn<ManageProductsTable, Float> col_ProductPrice;

    @FXML
    private TextField tf_ProductColor;

    @FXML
    private TextField tf_ProductModel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void Manage_Sales_Rep(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Rep.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Manage_Stocks(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Stocks.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }



    ObservableList<ManageProductsTable> listM;
    int index = -1;

    String sql = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ManageProductsTable stockTable = null;

    public void addProduct() {

        conn = mysqlconnect.ConnectDb();
        String sql = "insert into productdetails (productID,proName,proModel,proColor,proPrice) values(?, ?, ?,?,?)";

        if (tf_ProductID.getText().equals("") || tf_ProductName.getText().equals("") || tf_ProductModel.getText().equals("") || tf_ProductColor.getText().equals("") || tf_ProductPrice.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "please enter all data");
        } else {
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, tf_ProductID.getText().trim());
                pst.setString(2, tf_ProductName.getText().trim());
                pst.setString(3, tf_ProductModel.getText().trim());
                pst.setString(4, tf_ProductColor.getText().trim());
                pst.setString(5, String.valueOf(Float.parseFloat(tf_ProductPrice.getText().trim())));
                pst.execute();

                col_ProductID.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("productID"));
                Col_ProductName.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proName"));
                col_ProductModel.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proModel"));
                col_ProductColor.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proColor"));
                col_ProductPrice.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, Float>("proPrice"));


                listM = mysqlconnect.getdataManageProductsTable();
                Manage_Product.setItems(listM);

                JOptionPane.showMessageDialog(null, "Product Add succesfully.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            tf_ProductID.setText("");
            tf_ProductName.setText("");
            tf_ProductModel.setText("");
            tf_ProductColor.setText("");
            tf_ProductPrice.setText("");

        }
    }

    public void modifyProduct(ActionEvent event) {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = tf_ProductID.getText();
            String value2 = tf_ProductName.getText();
            String value3 = tf_ProductModel.getText();
            String value4 = tf_ProductColor.getText();
            String value5 = tf_ProductPrice.getText();

            String sql = "update productdetails set productID= '" + value1 + "', proName= '" + value2 + "',proModel= '" +
                    value3 + "', proColor= '" + value4 + "',proPrice= '" + value5 + "' where productID= '" + value1 + "' ";

            pst = conn.prepareStatement(sql);
            pst.execute();


            col_ProductID.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("productID"));
            Col_ProductName.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proName"));
            col_ProductModel.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proModel"));
            col_ProductColor.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proColor"));
            col_ProductPrice.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, Float>("proPrice"));

            listM = mysqlconnect.getdataManageProductsTable();
            Manage_Product.setItems(listM);

            JOptionPane.showMessageDialog(null, "Product update succesfully.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

        tf_ProductID.setText("");
        tf_ProductName.setText("");
        tf_ProductModel.setText("");
        tf_ProductColor.setText("");
        tf_ProductPrice.setText("");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_ProductID.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("productID"));
        Col_ProductName.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proName"));
        col_ProductModel.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proModel"));
        col_ProductColor.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proColor"));
        col_ProductPrice.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, Float>("proPrice"));


        listM = mysqlconnect.getdataManageProductsTable();
        Manage_Product.setItems(listM);
    }


    public void onclick_modifyProduct(MouseEvent mouseEvent) {


        index = Manage_Product.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tf_ProductID.setText(col_ProductID.getCellData(index).toString());
        tf_ProductName.setText(Col_ProductName.getCellData(index).toString());
        tf_ProductModel.setText(col_ProductModel.getCellData(index).toString());
        tf_ProductColor.setText(col_ProductColor.getCellData(index).toString());
        tf_ProductPrice.setText(col_ProductPrice.getCellData(index).toString());
    }


    public void DeleteProduct(ActionEvent event) {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = tf_ProductID.getText();

            String sql = "DELETE FROM productdetails WHERE productID= '" + value1 + "' ";

            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        tf_ProductID.setText("");
        tf_ProductName.setText("");
        tf_ProductModel.setText("");
        tf_ProductColor.setText("");
        tf_ProductPrice.setText("");
        col_ProductID.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("productID"));
        Col_ProductName.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proName"));
        col_ProductModel.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proModel"));
        col_ProductColor.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, String>("proColor"));
        col_ProductPrice.setCellValueFactory(new PropertyValueFactory<ManageProductsTable, Float>("proPrice"));


        listM = mysqlconnect.getdataManageProductsTable();
        Manage_Product.setItems(listM);
    }
}


