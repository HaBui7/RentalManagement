package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file from the views folder
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example.demo/login.fxml"));
        Parent root = fxmlLoader.load();

        // Create a Scene
        Scene scene = new Scene(root);

        // Add stylesheets
        String css = getClass().getResource("/org.example.demo/style.css").toExternalForm();
        String mobileCss = getClass().getResource("/org.example.demo/mobile.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Get reference to the BorderPane and the left side from the FXML
        BorderPane borderPane = (BorderPane) root;
        VBox leftSide = (VBox) borderPane.lookup("#leftSide");
        borderPane.setCenter(null);
        // Add responsiveness logic
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 600) {
                // Adjust stylesheets for smaller screens
                scene.getStylesheets().clear();
                scene.getStylesheets().add(mobileCss);

                // Remove the left side to allow the center to take full space
                if (leftSide != null) {
                    leftSide.setVisible(false);

                    borderPane.setLeft(null);
                }
            } else {
                // Reapply default stylesheet for larger screens
                scene.getStylesheets().clear();
                scene.getStylesheets().add(css);

                // Restore the left side and ensure visibility
                if (leftSide != null) {
                    leftSide.setVisible(true);
                    borderPane.setLeft(leftSide);
                }
            }
        });

        // Set up the Stage
        stage.setTitle("Login View");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
