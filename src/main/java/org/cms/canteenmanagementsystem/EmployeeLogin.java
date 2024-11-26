package org.cms.canteenmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmployeeLogin implements Initializable {
    @FXML
    private Button CloseBtn;
    @FXML
    private TextField NameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private ImageView myImageView;
    HashMap<String,String> loginInfo = new HashMap<>();
    Encryptor encryptor  = new Encryptor();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File myFile = new File("images/home-background-image.png");
        Image myImage = new Image(myFile.toURI().toString());
        myImageView.setImage(myImage);
    }

    private void updateLoginUserNamesAndPassword() throws IOException{
        loginInfo.clear();
        loginInfo = new HashMap<>();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Name, Password FROM employeeaccount");
            //statement.executeUpdate("SELECT username, password FROM employeeaccount");

            while (resultSet.next()) {
                String username = resultSet.getString("Name");
                String password = resultSet.getString("Password");

                loginInfo.put(username, password);
            }

            // Close the ResultSet, Statement, and Connection objects
            resultSet.close();
            statement.close();
            connectDB.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        String username = NameTF.getText();
        String password = passwordTF.getText();
        updateLoginUserNamesAndPassword();
        String encryptedPassword = loginInfo.get(username);

        if(encryptor.encryptString(password).equals(encryptedPassword) && EmailTF.getText().isBlank()==false && NameTF.getText().isBlank()==false)
        {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login Confirmation Message");
                        alert.setHeaderText("EMPLOYEE LOGIN");
                        alert.setContentText("Logged in sucessfully.");
                        alert.showAndWait();

                        NameTF.setText("");
                        passwordTF.setText("");
                        EmailTF.setText("");

                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeePage.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Node oldButton =(Node) event.getSource () ;
                        Stage myStage = (Stage)oldButton.getScene().getWindow();
                        myStage.setScene(scene);
                        myStage.show();

                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login Error Message");
                        alert.setHeaderText("EMPLOYEE LOGIN");
                        alert.setContentText("Oops! Invalid Login, Please try again.");
                        alert.showAndWait();

                        passwordTF.setText("");
                        EmailTF.setText("");

                    }
    }
    public void cancelButtonOnAction(){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
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
