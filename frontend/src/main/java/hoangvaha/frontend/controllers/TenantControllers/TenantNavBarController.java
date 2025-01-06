package hoangvaha.frontend.controllers.TenantControllers;

import javafx.fxml.FXML;

public class TenantNavBarController {

    private TenantMainLayoutController mainController;

    public void setMainController(TenantMainLayoutController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleHome() {
        mainController.loadPage("/hoangvaha/frontend/fxml/tenant/tenantHomePage.fxml");
    }

    @FXML
    private void handleProfile() {
        mainController.loadPage("/hoangvaha/frontend/fxml/userProfile.fxml");
    }
    @FXML
    private void handleRentedProperty(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenant/tenantRentedProperty.fxml");
    }
    @FXML
    private void handleTenantRA(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenant/tenantRentalAgreement.fxml");
    }
    @FXML
    private void handleTenantPayments(){
        mainController.loadPage("/hoangvaha/frontend/fxml/tenant/tenantPayment.fxml");
    }
}
