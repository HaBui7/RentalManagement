package hoangvaha.frontend.controllers.TenantControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class TenantMainLayoutController {
    @FXML
    private StackPane contentArea;

    @FXML
    private TenantNavBarController navBarController;

    @FXML
    public void initialize() {
        // The fx:include automatically loads the tenantNavBar.fxml, and JavaFX sets the controller.
        navBarController.setMainController(this); // Link the MainLayoutController
        try {
            // Automatically load the homepage on initialization
            loadPage("/hoangvaha/frontend/fxml/tenant/tenantHomePage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
