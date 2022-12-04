
    package sample;

            import javafx.collections.ObservableList;
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
            import java.util.ResourceBundle;

public class Manage_Rep implements Initializable {
    @FXML
    private TextField tf_Mobile;

    @FXML
    private TextField tf_Address;

    @FXML
    private TextField tf_area;

    @FXML
    private TableView<ManageSalesRepTable> manage_rep;
    @FXML
    private TableColumn<ManageSalesRepTable,Integer> col_ID;

    @FXML
    private TableColumn<ManageSalesRepTable,String> col_Name;

    @FXML
    private TableColumn<ManageSalesRepTable, Integer> col_Mobile;

    @FXML
    private TableColumn<ManageSalesRepTable, String> col_Address;

    @FXML
    private TableColumn<ManageSalesRepTable,String> col_area;

    @FXML
    private TextField tf_Id;

    @FXML
    private TextField tf_Name;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void Manage_Products(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Products.fxml"));
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
    ObservableList<ManageSalesRepTable> list2;
    int index = -1;

    String sql = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ManageSalesRepTable repTable = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_ID.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, Integer>("srID"));
        col_Name.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srName"));
        col_Mobile.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, Integer>("srMobile"));
        col_Address.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srAddress"));
        col_area.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("salesArea"));


        list2 = mysqlconnect.getdataManageSalesRepTable();
        manage_rep.setItems(list2);
    }

    public void modify_salesrep(javafx.event.ActionEvent event) {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 =tf_Id.getText();
            String value2 = tf_Name.getText();
            String value3 = tf_Mobile.getText();
            String value4 = tf_Address.getText();
            String value5 = tf_area.getText();

            String sql = "update salesrep set srID= '" + value1 + "', srName= '" + value2 + "',srMobile= '" +
                    value3 + "', srAddress= '" + value4 + "',salesArea= '" + value5 + "' where srID= '" + value1 + "' ";

            pst = conn.prepareStatement(sql);
            pst.execute();

            col_ID.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, Integer>("srID"));
            col_Name.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srName"));
            col_Mobile.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable,Integer>("srMobile"));
            col_Address.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srAddress"));
            col_area.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("salesArea"));

            list2 = mysqlconnect.getdataManageSalesRepTable();
            manage_rep.setItems(list2);

            JOptionPane.showMessageDialog(null, "Product update succesfully.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        tf_Id.setText("");
        tf_Name.setText("");
        tf_Mobile.setText("");
        tf_Address.setText("");
        tf_area.setText("");

    }

    public void add_salesrep(javafx.event.ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into salesrep (srID,srName,srMobile,srAddress,salesArea,srPassword) values(?, ?, ?,?,?,'1234')";

        if (tf_Id.getText().equals("") || tf_Name.getText().equals("") || tf_Mobile.getText().equals("") || tf_Address.getText().equals("") || tf_area.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "please enter all data");
        } else {
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, tf_Id.getText().trim());
                pst.setString(2, tf_Name.getText().trim());
                pst.setString(3,tf_Mobile.getText().trim());
                pst.setString(4, tf_Address.getText().trim());
                pst.setString(5,tf_area.getText().trim() );
                pst.execute();

                col_ID.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, Integer>("srID"));
                col_Name.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srName"));
                col_Mobile.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, Integer>("srMobile"));
                col_Address.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("srAddress"));
                col_area.setCellValueFactory(new PropertyValueFactory<ManageSalesRepTable, String>("salesArea"));



                list2 = mysqlconnect.getdataManageSalesRepTable();
                manage_rep.setItems(list2);

                JOptionPane.showMessageDialog(null, "Product Add succesfully.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            tf_Id.setText("");
            tf_Name.setText("");
            tf_Address.setText("");
            tf_Mobile.setText("");
            tf_area.setText("");

        }
    }


    @FXML
    void setdata_salesRep(MouseEvent event) {

        index = manage_rep.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tf_Id.setText(col_ID.getCellData(index).toString());
        tf_Name.setText(col_Name.getCellData(index).toString());
        tf_Mobile.setText(col_Mobile.getCellData(index).toString());
        tf_Address.setText(col_Address.getCellData(index).toString());
        tf_area.setText(col_area.getCellData(index).toString());
    }
}
