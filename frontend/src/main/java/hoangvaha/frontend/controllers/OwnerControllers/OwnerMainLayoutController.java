package hoangvaha.frontend.controllers.OwnerControllers;

import hoangvaha.frontend.controllers.OwnerControllers.OwnerNavBarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class OwnerMainLayoutController {
    @FXML
    private StackPane contentArea;

    @FXML
    private OwnerNavBarController navBarController;

    @FXML
    public void initialize() {
        // The fx:include automatically loads the visitorNavBar.fxml, and JavaFX sets the controller.
        navBarController.setMainController(this); // Link the MainLayoutController
        try {
            // Automatically load the homepage on initialization
            loadPage("/hoangvaha/frontend/fxml/owner/ownerHomePage.fxml");
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
