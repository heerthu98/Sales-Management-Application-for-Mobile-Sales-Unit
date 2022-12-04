package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SalesRep extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SalesRep.fxml"));
        primaryStage.setTitle("Sales Management System");
        primaryStage.setScene(new Scene(root, 1040, 570));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
