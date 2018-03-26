package lab_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.sql.Statement;

public class SignUp {
    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public SignUp() {
        connection = ConnectionUtil.connectdb();
    }

    @FXML
    private void buttonSignUp(ActionEvent event) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        String sql = "SELECT * FROM employee WHERE email = ? and password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                infoBox("This e-mail is reserved", "Failed", null);
            }else{
                String sql1 = "INSERT INTO employee (email, password) VALUES(? ,?)";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();

                /*String query = "INSERT INTO swingapp.employee (email, password) \n" +
                        " VALUES('" + email + "','" + password + ");";
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(query);*/

                infoBox("SignUp Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}
