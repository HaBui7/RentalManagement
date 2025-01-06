package hoangvaha.frontend.controllers.OwnerControllers;

import javafx.fxml.FXML;
import hoangvaha.frontend.controllers.OwnerControllers.OwnerMainLayoutController;

public class OwnerNavBarController {

    private OwnerMainLayoutController mainController;

    public void setMainController(OwnerMainLayoutController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleHome() {
        mainController.loadPage("/hoangvaha/frontend/fxml/owner/ownerHomePage.fxml");
    }

    @FXML
    private void handleProfile() {
        mainController.loadPage("/hoangvaha/frontend/fxml/userProfile.fxml");
    }

    @FXML
    private void handleViewProperty() {
        mainController.loadPage("/hoangvaha/frontend/fxml/owner/ownerViewProperty.fxml");
    }

    @FXML
    private void handleAddProperty() {
        mainController.loadPage("/hoangvaha/frontend/fxml/owner/ownerAddProperty.fxml");
    }
}
