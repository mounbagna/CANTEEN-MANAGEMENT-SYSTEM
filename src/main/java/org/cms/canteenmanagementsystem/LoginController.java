package org.cms.canteenmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView myImageView;
    HashMap<String,String> loginInfo = new HashMap<>();
    Encryptor encryptor  = new Encryptor();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File myFile = new File("images/first page background.PNG");
        Image myImage = new Image(myFile.toURI().toString());
        myImageView.setImage(myImage);
    }
    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if (!userNameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String username = userNameTextField.getText();
            String enteredPassword = passwordTextField.getText();

            // Query to fetch the hashed password from the database
            String getHashedPasswordQuery = "SELECT password FROM adminaccount WHERE Name = '" + username + "'";

            try {
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery(getHashedPasswordQuery);

                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password");
                    String enteredHashedPassword = encryptor.encryptString(enteredPassword); // Hash entered password

                    // Compare the hashes
                    if (storedHashedPassword.equals(enteredHashedPassword)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login Confirmation Message");
                        alert.setHeaderText("ADMIN LOGIN");
                        alert.setContentText("Logged in successfully.");
                        alert.showAndWait();

                        userNameTextField.setText("");
                        passwordTextField.setText("");

                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminMenu.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        Node oldButton = (Node) event.getSource();
                        Stage myStage = (Stage) oldButton.getScene().getWindow();
                        myStage.setScene(scene);
                        myStage.show();
                    } else {
                        showErrorAlert("Invalid Login", "Incorrect username or password.");
                        passwordTextField.setText("");
                    }
                } else {
                    showErrorAlert("Invalid Login", "No user found with this username.");
                    passwordTextField.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showErrorAlert("Login Error", "Please enter your username and password.");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
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