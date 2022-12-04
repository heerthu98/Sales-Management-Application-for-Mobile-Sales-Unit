
package sample;

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
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

        import javax.swing.*;
        import java.io.IOException;
        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.util.ResourceBundle;

public class Manage_Stocks implements Initializable {
    @FXML
    private TextField tf_productID;

    @FXML
    private TextField tf_proName;

    @FXML
    private TextField tf_count;



    @FXML
    private TableView<ManageStocksTable> manage_stocks;

    @FXML
    private TableColumn<ManageStocksTable, String> col_productID;

    @FXML
    private TableColumn<ManageStocksTable, String> col_proName;

    @FXML
    private TableColumn<ManageStocksTable, Integer> col_count;



    @FXML
    private ComboBox<String> tf_status;

    @FXML
    private DatePicker tf_date;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void Manage_Products(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Products.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Manage_Sales_Rep(javafx.event.ActionEvent  event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Rep.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }


    ObservableList<ManageStocksTable> listS;
    int index = -1;

    String sql = null;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ManageStocksTable stockTable = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_status.getItems().addAll("in","out");

        col_productID.setCellValueFactory(new PropertyValueFactory<ManageStocksTable, String>("productID"));
        col_count.setCellValueFactory(new PropertyValueFactory<ManageStocksTable, Integer>("total"));
        //  col_count.setCellValueFactory(new PropertyValueFactory<ManageStocksTable,Float>("total"));


        listS = mysqlconnect.getdataManageStocksTable();
        manage_stocks.setItems(listS);
    }
    public int update_Stock(ActionEvent event) {


        conn = mysqlconnect.ConnectDb();
        String sql = "insert into totalstock (productID,count,stockStatus,date) values(?, ?, ?,?)";

        if (tf_count.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "please enter all data");
        } else {

            try {
                int a = 0;
                String status=tf_status.getSelectionModel().getSelectedItem().toString();
                if(status=="out"){
                    Integer c=Integer.parseInt(tf_count.getText().trim());
                    a=c-2*c;
                }
                else {
                    return a;
                }
                pst = conn.prepareStatement(sql);
                pst.setString(1, tf_productID.getText().trim());
                pst.setString(2, String.valueOf(a));
                pst.setString(3,tf_status.getSelectionModel().getSelectedItem().toString());
                pst.setString(4, String.valueOf(tf_date.getValue()));
                pst.execute();


                col_productID.setCellValueFactory(new PropertyValueFactory<ManageStocksTable, String>("productID"));
                col_count.setCellValueFactory(new PropertyValueFactory<ManageStocksTable, Integer>("total"));

                listS = mysqlconnect.getdataManageStocksTable();
                manage_stocks.setItems(listS);

                JOptionPane.showMessageDialog(null, "Product update succesfully.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            tf_proName.setText("");
            tf_productID.setText("");
            tf_count.setText("");


        }

        return 0;
    }

    public void on_clicked_stock(MouseEvent mouseEvent) {
        index = manage_stocks.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        tf_productID.setText(col_productID.getCellData(index).toString());
        tf_proName.setText(col_proName.getCellData(index).toString());
        // tf_ProductModel.setText(col_ProductModel.getCellData(index).toString());
        // tf_ProductColor.setText(col_ProductColor.getCellData(index).toString());
        // tf_ProductPrice.setText(col_ProductPrice.getCellData(index).toString());
    }


}

