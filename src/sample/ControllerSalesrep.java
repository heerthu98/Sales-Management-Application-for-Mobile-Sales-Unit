package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerSalesrep {
    private Stage stage;
    private Scene scene;
    private Object root;


    public void DailyReport(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DailyReport.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void ManageStocks(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PersonalStock.fxml"));
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

}
