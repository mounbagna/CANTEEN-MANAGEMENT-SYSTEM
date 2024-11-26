package org.cms.canteenmanagementsystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenu implements Initializable {
    @FXML
    private ImageView AdminImageView;
    @FXML
    private Button CloseBtn;
    @FXML
    private Button BackBtn;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File welcomeImageFile = new File("images/full-page-background.PNG");
        Image welcomeImage = new Image(welcomeImageFile.toURI().toString());
        AdminImageView.setImage(welcomeImage);
    }
    @FXML
    public void FoodMenuBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FoodMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    @FXML
    public void CustomerMenuBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }

    @FXML
    public void EmployeeMenuBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    @FXML
    public void CloseBtn(ActionEvent event){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    @FXML
    public void BackBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
}
