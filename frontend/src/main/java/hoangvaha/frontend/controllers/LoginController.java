package hoangvaha.frontend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public void goToRegister(ActionEvent event) {
        try {
            // Load the register.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hoangvaha/frontend/fxml/register.fxml"));
            Scene registerScene = new Scene(fxmlLoader.load());

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(registerScene);
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the error appropriately
        }
    }
}
