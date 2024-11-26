package org.cms.canteenmanagementsystem;

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

public class WelcomeScreenController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private ImageView myImageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File myFile = new File("images/cafePicture.png");
        Image myImage = new Image(myFile.toURI().toString());
        myImageView.setImage(myImage);
    }
    @FXML
    public void gotoRegisterMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    @FXML
    public void gotoLoginMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    @FXML
    public void CustomerLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerLoginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void EmployeeLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }

    @FXML
    public void EmployeeRegistration(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }

}
