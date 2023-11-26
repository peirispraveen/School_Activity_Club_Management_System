package com.example.sacms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-members.fxml"));
        Stage newStage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Advisor Sign In");
        newStage.setScene(newScene);
        newStage.show();

        try{
            UserRegMainController controller = userRegLoader.getController();
            controller.loadStudentsFromFile();
            controller.loadAdvisorsFromFile();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
