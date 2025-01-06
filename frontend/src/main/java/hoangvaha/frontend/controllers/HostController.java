package hoangvaha.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public class HostController implements Initializable {

    // --- TextFields (Add Section) ---
    @FXML
    private TextField mainTenant; // e.g. "John Doe"

    @FXML
    private TextField subTenant;  // if needed

    // --- ComboBoxes (Add Section) ---
    @FXML
    private ComboBox<String> ownerChoice;
    @FXML
    private ComboBox<String> propertyIdChoice;
    @FXML
    private ComboBox<String> hostIdchoice;
    @FXML
    private ComboBox<String> periodChoice;
    @FXML
    private ComboBox<String> rentingFee;
    @FXML
    private ComboBox<String> statusChoice;

    // --- Buttons ---
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;   // The same button used for both "Search" and then "Save"
    @FXML
    private Button removeButton;

    // --- Fields for the "Update" section in FXML ---
    @FXML
    private TextField rentalIdInput1;    // The user types the Rental ID here
    @FXML
    private TextField rentalIdInput11; // The TextField for the user to type the Rental ID to remove
    @FXML
    private ComboBox<String> ownerChoice1;
    @FXML
    private TextField subTenant1;
    @FXML
    private ComboBox<String> periodChoice1;

    // Possibly you have more fields for host, fee, etc.
    // Just follow the same pattern if you want them to be editable in Update as well.

    // --- Internal fields to track state for updating ---
    private boolean isUpdateMode = false;
    private int recordIndexToUpdate = -1;
    private List<String> allLines = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1) Populate combo boxes with sample data (Add Section)
        ownerChoice.getItems().addAll("Owner A", "Owner B", "Owner C");
        propertyIdChoice.getItems().addAll("Property-1", "Property-2", "Property-3");
        hostIdchoice.getItems().addAll("Host-1", "Host-2", "Host-3");
        periodChoice.getItems().addAll("6 months", "12 months", "24 months");
        rentingFee.getItems().addAll("100", "200", "300");
        statusChoice.getItems().addAll("Active", "Terminated", "Closed");

        // 2) Populate combo boxes with sample data (Update Section) – if needed
        ownerChoice1.getItems().addAll("Owner A", "Owner B", "Owner C");
        periodChoice1.getItems().addAll("6 months", "12 months", "24 months");
        // etc. for any other update comboBoxes

        // 3) Wire up button actions
        addButton.setOnAction(this::onAddClicked);
        updateButton.setOnAction(this::onUpdateClicked);
        removeButton.setOnAction(this::onRemoveClicked);
    }

    /**
     * Called when user clicks "Add" button.
     * Example: gather data from fields and append to a CSV file.
     */
    private void onAddClicked(ActionEvent event) {
        String mTenant = mainTenant.getText().trim();
        String owner   = ownerChoice.getValue();
        String property= propertyIdChoice.getValue();
        String host    = hostIdchoice.getValue();
        String period  = periodChoice.getValue();
        String fee     = rentingFee.getValue();
        String status  = statusChoice.getValue();

        // (Optional) generate or prompt for a Rental ID here if you want to store it as well.
        // For example: "R1234". The example below does *not* show a rentalId,
        // but if you do have one, just prepend it to csvLine.

        // In your code, you currently do:
        // property, owner, host, mTenant, period, fee, status
        String csvLine = String.join(",",
                property,
                owner,
                host,
                mTenant,
                period,
                fee,
                status
        );

        // Append to "rental.csv"
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv"),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)
        ) {
            writer.write(csvLine);
            writer.newLine();
            System.out.println("Appended to CSV: " + csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear fields or do any other post-add logic
        clearFields();
    }

    /**
     * Called when user clicks the "Update" button.
     * This method is used in two phases:
     *  - Phase 1: Search for the Rental ID and populate fields if found.
     *  - Phase 2: Save the edited fields back to the CSV.
     */
    private void onUpdateClicked(ActionEvent event) {
        // If we're NOT in update mode yet, it means "Search for record".
        if (!isUpdateMode) {
            String searchId = rentalIdInput1.getText().trim();
            if (searchId.isEmpty()) {
                System.out.println("Please enter a Rental ID before updating.");
                return;
            }

            // Load all lines from CSV.
            try {
                allLines = Files.readAllLines(Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv"));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Search for the line whose first column == searchId
            boolean found = false;
            for (int i = 0; i < allLines.size(); i++) {
                String line = allLines.get(i);
                String[] parts = line.split(",");

                // Check if the first column matches the user input.
                // Adjust the index if your CSV structure is different.
                if (parts.length > 0 && parts[0].equals(searchId)) {
                    // Found the matching record.
                    recordIndexToUpdate = i;
                    found = true;

                    // Populate UI fields so user can see & edit them.
                    // Adjust indices as needed for your CSV structure.
                    if (parts.length > 1) ownerChoice1.setValue(parts[1]);  // e.g. Owner
                    if (parts.length > 3) subTenant1.setText(parts[3]);     // e.g. MainTenant or SubTenant
                    if (parts.length > 4) periodChoice1.setValue(parts[4]); // e.g. Period

                    // Switch to "save" mode
                    isUpdateMode = true;
                    updateButton.setText("Save");
                    System.out.println("Record found. You can now edit the fields and click 'Save'.");
                    break;
                }
            }

            if (!found) {
                System.out.println("No record found with Rental ID: " + searchId);
            }
        }
        else {
            // We are in "save" mode now. That means user has edited the fields, and we write them back.
            if (recordIndexToUpdate < 0 || recordIndexToUpdate >= allLines.size()) {
                System.out.println("Invalid record index. Cannot save.");
                return;
            }
            // Get the original line and parse it
            String originalLine = allLines.get(recordIndexToUpdate);
            String[] parts = originalLine.split(",");

            // Update only the columns you want to allow the user to change.
            // E.g., if your CSV is:
            //   [0] rentalId, [1] owner, [2] host, [3] tenant, [4] period, [5] fee, [6] status
            // and the user can change just [1], [3], [4], etc. then do:
            if (parts.length > 1) parts[1] = ownerChoice1.getValue();
            if (parts.length > 3) parts[3] = subTenant1.getText();
            if (parts.length > 4) parts[4] = periodChoice1.getValue();

            // Rebuild the CSV line
            String updatedLine = String.join(",", parts);
            allLines.set(recordIndexToUpdate, updatedLine);

            // Write all lines back to the CSV
            try {
                Files.write(
                        Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv"),
                        allLines,
                        StandardOpenOption.TRUNCATE_EXISTING
                );
                System.out.println("Record updated successfully: " + updatedLine);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Reset our flags and UI
            isUpdateMode = false;
            recordIndexToUpdate = -1;
            updateButton.setText("Update");
            clearUpdateFields();
        }
    }

    /**
     * Called when user clicks the "Remove" button.
     * It removes the record from the CSV based on the Rental ID.
     */
    private void onRemoveClicked(ActionEvent event) {
        // 1) Read the Rental ID from the text field
        String rentalIdToRemove = rentalIdInput11.getText().trim();

        if (rentalIdToRemove.isEmpty()) {
            System.out.println("Please enter a Rental ID before removing.");
            return;
        }

        // 2) Read all lines from the CSV file
        List<String> allLines = new ArrayList<>();
        Path csvPath = Paths.get("src/main/resources/hoangvaha/frontend/sample/rental.csv");
        try {
            allLines = Files.readAllLines(csvPath);
        } catch (IOException e) {
            e.printStackTrace();
            return; // or show an alert, etc.
        }

        // 3) Find and remove the line that starts with the matching rental ID
        //    (assuming the rentalId is in parts[0])
        boolean removedAny = false;
        Iterator<String> iterator = allLines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equals(rentalIdToRemove)) {
                iterator.remove();
                removedAny = true;
            }
        }

        // 4) If we removed a line, write the new list back to CSV
        if (removedAny) {
            try {
                Files.write(csvPath, allLines, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Removed record(s) with Rental ID = " + rentalIdToRemove);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No record found with Rental ID: " + rentalIdToRemove);
        }

        // 5) Clear the text field or any other UI as needed
        rentalIdInput11.clear();
    }


    /**
     * Clears the fields from the "Add Rental" section.
     */
    private void clearFields() {
        mainTenant.clear();
        subTenant.clear();  // If used

        ownerChoice.getSelectionModel().clearSelection();
        propertyIdChoice.getSelectionModel().clearSelection();
        hostIdchoice.getSelectionModel().clearSelection();
        periodChoice.getSelectionModel().clearSelection();
        rentingFee.getSelectionModel().clearSelection();
        statusChoice.getSelectionModel().clearSelection();
    }

    /**
     * Clears the fields from the "Update Rental" section.
     */
    private void clearUpdateFields() {
        rentalIdInput1.clear();
        ownerChoice1.getSelectionModel().clearSelection();
        subTenant1.clear();
        periodChoice1.getSelectionModel().clearSelection();
        // etc. for any other fields used in update
    }
}
