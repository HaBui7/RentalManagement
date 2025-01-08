package hoangvaha.frontend.controllers.ManagerController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;


public class ManagerController implements Initializable {

    // =========================
    // FXML Fields (Add Section)
    // =========================
    @FXML
    private TextField mainTenant;       // fx:id="mainTenant"
    @FXML
    private TextField subTenant;        // fx:id="subTenant"

    @FXML
    private ComboBox<String> ownerChoice;     // fx:id="ownerChoice"
    @FXML
    private ComboBox<String> propertyIdChoice;// fx:id="propertyIdChoice"
    @FXML
    private ComboBox<String> hostIdchoice;    // fx:id="hostIdchoice"
    @FXML
    private ComboBox<String> periodChoice;    // fx:id="periodChoice"
    @FXML
    private ComboBox<String> rentingFee;      // fx:id="rentingFee"
    @FXML
    private ComboBox<String> statusChoice;    // fx:id="statusChoice"

    @FXML
    private Button addButton;    // fx:id="addButton"


    // ============================
    // FXML Fields (Update Section)
    // ============================
    @FXML
    private TextField rentalIdInput1;    // fx:id="rentalIdInput1"
    @FXML
    private TextField subTenant1;        // fx:id="subTenant1"
    @FXML
    private ComboBox<String> ownerChoice1;  // fx:id="ownerChoice1"
    @FXML
    private ComboBox<String> periodChoice1; // fx:id="periodChoice1"

    @FXML
    private Button updateButton;  // fx:id="updateButton"


    // ============================
    // FXML Fields (Remove Section)
    // ============================
    @FXML
    private TextField rentalIdInput11;  // fx:id="rentalIdInput11"
    @FXML
    private Button removeButton;        // fx:id="removeButton"


    // ===================================================
    // Internal fields for tracking "Update" state
    // ===================================================
    private boolean isUpdateMode = false;
    private int recordIndexToUpdate = -1;
    private List<String> allLines = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate ComboBoxes (example data)

        // Wire up button actions

    }


}
