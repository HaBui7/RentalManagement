package hoangvaha.frontend.controllers.HostController;

import hoangvaha.frontend.controllers.ManagerController.ManagerMainLayoutController;
import javafx.fxml.FXML;

public class HostNavBarController {
    private HostMainLayoutController mainController;

    public void setMainController(HostMainLayoutController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleHomePage() {
        mainController.loadPage("/hoangvaha/frontend/fxml/host/hostChoiceStatistic.fxml");
    }

    @FXML
    private void handleRental(){
        mainController.loadPage("/hoangvaha/frontend/fxml/host/hostManageRental.fxml");
    }

    @FXML
    private void handleProperty(){
        mainController.loadPage("/hoangvaha/frontend/fxml/host/hostManageProperty.fxml");
    }

    @FXML
    private void handleEntity(){
        mainController.loadPage("/hoangvaha/frontend/fxml/host/hostChoiceStatistic.fxml");
    }

    @FXML
    private void handleResidentialForm(){
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerManageResidential.fxml");
    }
    @FXML
    private void handleCommercialForm(){
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerManageCommercial.fxml");
    }
}
