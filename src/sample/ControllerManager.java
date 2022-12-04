package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ControllerManager {

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

    @FXML
    void Manage_Stocks(javafx.event.ActionEvent  event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Manage_Stocks.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }


}
