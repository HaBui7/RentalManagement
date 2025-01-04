package hoangvaha.frontend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.Initializable;


import java.io.IOException;

public class RegisterController implements Initializable {
    @FXML
    private ChoiceBox<String> accountTypeChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add options to the ChoiceBox
        accountTypeChoiceBox.getItems().addAll("Tenant", "Host", "Owner");
        accountTypeChoiceBox.setOnAction(event -> {
            getType(event);
        });
    }

    public String getType(ActionEvent event) {
        String type = accountTypeChoiceBox.getValue();
        return type;
    }

    public void goToLogin(ActionEvent event) {
        try {
            // Load the register.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hoangvaha/frontend/fxml/login.fxml"));
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
