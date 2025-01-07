package hoangvaha.frontend.controllers.ManagerController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

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
        ownerChoice.getItems().addAll("Owner A", "Owner B", "Owner C");
        propertyIdChoice.getItems().addAll("Property-1", "Property-2", "Property-3");
        hostIdchoice.getItems().addAll("Host-1", "Host-2", "Host-3");
        periodChoice.getItems().addAll("6 months", "12 months", "24 months");
        rentingFee.getItems().addAll("100", "200", "300");
        statusChoice.getItems().addAll("Active", "Terminated", "Closed");

        // Similarly for update section (if needed):
        ownerChoice1.getItems().addAll("Owner A", "Owner B", "Owner C");
        periodChoice1.getItems().addAll("6 months", "12 months", "24 months");

        // Wire up button actions
        addButton.setOnAction(this::onAddClicked);
        updateButton.setOnAction(this::onUpdateClicked);
        removeButton.setOnAction(this::onRemoveClicked);
    }

    // ===============================================================
    // 1) ADD METHOD
    // ===============================================================
    private void onAddClicked(ActionEvent event) {
        // Gather user input
        String mTenant = mainTenant.getText().trim();
        String sTenant = subTenant.getText().trim();

        String owner   = ownerChoice.getValue();
        String prop    = propertyIdChoice.getValue();
        String host    = hostIdchoice.getValue();
        String period  = periodChoice.getValue();
        String fee     = rentingFee.getValue();
        String status  = statusChoice.getValue();

        // Build CSV line (adjust columns as needed)
        // If you also have a 'rentalId' for new entries, add it here
        String csvLine = String.join(",",
                prop,      // [0]
                owner,     // [1]
                host,      // [2]
                mTenant,   // [3]
                sTenant,   // [4]
                period,    // [5]
                fee,       // [6]
                status     // [7]
        );

        // Append to "rental.csv"
        Path csvPath = Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(csvLine);
            writer.newLine();
            System.out.println("Appended to CSV: " + csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear fields
        clearAddFields();
    }

    // ===============================================================
    // 2) UPDATE METHOD (Two-Step: Search then Save)
    // ===============================================================
    private void onUpdateClicked(ActionEvent event) {
        if (!isUpdateMode) {
            // PHASE 1: Searching for record
            String searchId = rentalIdInput1.getText().trim();
            if (searchId.isEmpty()) {
                System.out.println("Please enter a Rental ID before updating.");
                return;
            }

            // Load all lines
            Path csvPath = Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv");
            try {
                allLines = Files.readAllLines(csvPath);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Look for line whose first column (parts[0]) matches searchId
            boolean found = false;
            for (int i = 0; i < allLines.size(); i++) {
                String line = allLines.get(i);
                String[] parts = line.split(",");

                // If your CSV structure starts with the rentalId at [0]
                // e.g. "r-1, Property-1, Owner A, Host-1, Tenant1, SubT1, 6 months, 100, Active"
                // adjust accordingly. Here we assume parts[0] is the "rentalId"
                if (parts.length > 0 && parts[0].equals(searchId)) {
                    found = true;
                    recordIndexToUpdate = i;

                    // Populate the fields so user can edit them
                    // Adjust indices as needed for your CSV. For example:
                    if (parts.length > 1) ownerChoice1.setValue(parts[1]);
                    if (parts.length > 3) subTenant1.setText(parts[3]);   // etc.
                    if (parts.length > 4) periodChoice1.setValue(parts[4]);

                    // Switch to "save mode"
                    isUpdateMode = true;
                    updateButton.setText("Save");
                    System.out.println("Record found. Edit fields and click 'Save'.");
                    break;
                }
            }

            if (!found) {
                System.out.println("No record found with Rental ID: " + searchId);
            }
        } else {
            // PHASE 2: Save the changes back
            if (recordIndexToUpdate < 0 || recordIndexToUpdate >= allLines.size()) {
                System.out.println("Invalid record index. Cannot save.");
                return;
            }

            // Original line
            String originalLine = allLines.get(recordIndexToUpdate);
            String[] parts = originalLine.split(",");

            // Overwrite columns that you allow the user to change
            // For example, if your CSV is:
            //   [0] rentalId, [1] owner, [2] host, [3] tenant, [4] period, [5] fee, [6] status
            if (parts.length > 1) parts[1] = ownerChoice1.getValue();
            if (parts.length > 3) parts[3] = subTenant1.getText();
            if (parts.length > 4) parts[4] = periodChoice1.getValue();

            // Rebuild line
            String updatedLine = String.join(",", parts);
            allLines.set(recordIndexToUpdate, updatedLine);

            // Write back to CSV
            Path csvPath = Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv");
            try {
                Files.write(csvPath, allLines, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Record updated successfully: " + updatedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Reset
            isUpdateMode = false;
            recordIndexToUpdate = -1;
            updateButton.setText("Update");
            clearUpdateFields();
        }
    }

    // ===============================================================
    // 3) REMOVE METHOD
    // ===============================================================
    private void onRemoveClicked(ActionEvent event) {
        String rentalIdToRemove = rentalIdInput11.getText().trim();
        if (rentalIdToRemove.isEmpty()) {
            System.out.println("Please enter a Rental ID before removing.");
            return;
        }

        Path csvPath = Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv");
        List<String> lines;
        try {
            lines = Files.readAllLines(csvPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        boolean removedAny = false;
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] parts = line.split(",");
            // If rentalId is in parts[0], compare it
            if (parts.length > 0 && parts[0].equals(rentalIdToRemove)) {
                iterator.remove();
                removedAny = true;
            }
        }

        if (removedAny) {
            try {
                Files.write(csvPath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Removed record(s) with Rental ID = " + rentalIdToRemove);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No record found with Rental ID: " + rentalIdToRemove);
        }

        // Clear the remove text field
        rentalIdInput11.clear();
    }


    // ======================================
    // Helper: Clear Fields
    // ======================================
    private void clearAddFields() {
        mainTenant.clear();
        subTenant.clear();

        ownerChoice.getSelectionModel().clearSelection();
        propertyIdChoice.getSelectionModel().clearSelection();
        hostIdchoice.getSelectionModel().clearSelection();
        periodChoice.getSelectionModel().clearSelection();
        rentingFee.getSelectionModel().clearSelection();
        statusChoice.getSelectionModel().clearSelection();
    }

    private void clearUpdateFields() {
        rentalIdInput1.clear();
        ownerChoice1.getSelectionModel().clearSelection();
        subTenant1.clear();
        periodChoice1.getSelectionModel().clearSelection();
        updateButton.setText("Update");
    }
}
