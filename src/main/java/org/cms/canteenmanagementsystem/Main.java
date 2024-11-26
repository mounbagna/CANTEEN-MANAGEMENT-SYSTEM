package org.cms.canteenmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene( new Scene(root,1000,900));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}