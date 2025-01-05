package hoangvaha.frontend.controllers;

import javafx.fxml.FXML;

public class TenantNavBarController {

    private MainLayoutController mainController;

    public void setMainController(MainLayoutController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleHome() {
        mainController.loadPage("/hoangvaha/frontend/fxml/tenantHomePage.fxml");
    }

    @FXML
    private void handleProfile() {
        mainController.loadPage("/hoangvaha/frontend/fxml/userProfile.fxml");
    }
    @FXML
    private void handleRentedProperty(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenantRentedProperty.fxml");
    }
    @FXML
    private void handleTenantRA(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenantRentalAgreement.fxml");
    }
    @FXML
    private void handleTenantPayments(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenantPayment.fxml");
    }
}
