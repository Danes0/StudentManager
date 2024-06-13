package org.example.studentmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Locale.setDefault(Locale.ENGLISH);

        // Load view from FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/studentmanager/MainView.fxml"));
        Parent root = loader.load();

        // Set up the scene and setting
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
