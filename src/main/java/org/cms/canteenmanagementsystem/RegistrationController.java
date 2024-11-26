package org.cms.canteenmanagementsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField TelephoneTextField;
    @FXML
    private TextField AdressTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button closeButton;
    HashMap<String,String> loginInfo = new HashMap<>();
    Encryptor encryptor  = new Encryptor();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }
    public void registerButtonOnAction(ActionEvent event) throws NoSuchAlgorithmException {
        if(passwordTextField.getText().equals(confirmPasswordTextField.getText())){
            //registerUser();
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String fullName = fullNameTextField.getText();
            String password = passwordTextField.getText();
            String Email = EmailTextField.getText();
            String Adress = AdressTextField.getText();
            String Telephone = TelephoneTextField.getText();

            String insertValues = fullName + "','" + encryptor.encryptString(password) + "','" + Email + "','" + Telephone + "','" + Adress +"')";
            String insertFields = "INSERT into useraccount(user_Name,password,Email,Telephone,Adress) VALUES ('";
            String insertRegister = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertRegister);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Confirmation Message");
                alert.setHeaderText("CUSTOMER REGISTRATION");
                alert.setContentText("The Customer has been registered successfully.");
                alert.showAndWait();

                fullNameTextField.setText("");
                passwordTextField.setText("");
                EmailTextField.setText("");
                TelephoneTextField.setText("");
                AdressTextField.setText("");
                confirmPasswordTextField.setText("");
            }
            catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Error Message");
            alert.setHeaderText("CUSTOMER REGISTRATION");
            alert.setContentText("Sorry! Password does not match.");
            alert.showAndWait();

            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
            EmailTextField.setText("");
            TelephoneTextField.setText("");
            AdressTextField.setText("");
        }

    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    //public void registerUser(){}
    @FXML
    public void BackBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Welcomescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
}
