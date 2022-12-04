package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class Login implements Initializable {

      public Login(){

    }
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Login(ActionEvent event) throws IOException {
        Login();

    }

    private void Login() throws IOException {
        Main m = new Main();

        conn = mysqlconnect.ConnectDb();
        String sql1 = "select * from  manager WHERE username = ? AND password = ? ";
        String sql2 = "select * from  salesrep WHERE srID = ? AND srPassword = ? ";

        try {

            pst = conn.prepareStatement(sql1);

            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                wrongLogIn.setText("Sucess!!!!");
                //JOptionPane.showMessageDialog(null, "correct");

                m.changesScene("Manager.fxml");

            } else {

                pst = conn.prepareStatement(sql2);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                rs = pst.executeQuery();

                if (rs.next()) {

                    wrongLogIn.setText("Sucess!!!!");
                    //JOptionPane.showMessageDialog(null, "correct");

                    m.changesScene("SalesRep.fxml");

                } else {
                    wrongLogIn.setText("Failure!!!!");
                    //JOptionPane.showMessageDialog(null, "wrong");
                }
            }



        } catch (Exception e) {

        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
