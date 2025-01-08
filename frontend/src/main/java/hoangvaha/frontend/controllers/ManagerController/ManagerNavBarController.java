package hoangvaha.frontend.controllers.ManagerController;

import javafx.fxml.FXML;

public class ManagerNavBarController {
    private ManagerMainLayoutController mainController;

    public void setMainController(ManagerMainLayoutController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleProperty() {
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerManagePropertyChoice.fxml");
    }

    @FXML
    private void handleProfile() {
        mainController.loadPage("/hoangvaha/frontend/fxml/managerViewOption.fxml");
    }

    @FXML
    private void handleUser(){
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerManageUser.fxml");
    }
    @FXML
    private void handleRental(){
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerManageRental.fxml");
    }
    @FXML
    private void handleOption(){
        mainController.loadPage("/hoangvaha/frontend/fxml/manager/managerViewOption.fxml");
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
