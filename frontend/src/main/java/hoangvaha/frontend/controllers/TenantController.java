package hoangvaha.frontend.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TenantController {

    @FXML
    private RadioButton residentialRadioButton;

    @FXML
    private RadioButton commercialRadioButton;

    @FXML
    private TableView<Object> propertyTableView;

    private ObservableList<Property> residentialProperties;
    private ObservableList<CommercialProperty> commercialProperties;

    @FXML
    public void initialize() {
        // Initialize data
        residentialProperties = FXCollections.observableArrayList();
        commercialProperties = FXCollections.observableArrayList();

        // Set up toggle group
        ToggleGroup propertyTypeGroup = new ToggleGroup();
        residentialRadioButton.setToggleGroup(propertyTypeGroup);
        commercialRadioButton.setToggleGroup(propertyTypeGroup);

        // Load residential data
        residentialRadioButton.setSelected(true);
        loadResidentialDataFromCSV();
        setUpResidentialTable();

        // Toggle event handler
        residentialRadioButton.setOnAction(this::handleRadioButtonToggle);
        commercialRadioButton.setOnAction(this::handleRadioButtonToggle);
    }

    private void setUpResidentialTable() {
        propertyTableView.getColumns().clear(); // Clear existing columns

        addColumn("Owner", (Property property) -> property.getOwner());
        addColumn("Host", (Property property) -> property.getHost());
        addColumn("Address", (Property property) -> property.getAddress());
        addColumn("Available Bedrooms", (Property property) -> property.getAvailableBedrooms());
        addColumn("Kitchen Availability", (Property property) -> property.getKitchenAvailability());
        addColumn("Pet Friendliness", (Property property) -> property.getPetFriendliness());

        propertyTableView.setItems(FXCollections.observableArrayList(residentialProperties)); // Set data
    }

    private void setUpCommercialTable() {
        propertyTableView.getColumns().clear(); // Clear existing columns

        addColumn("Owner", (CommercialProperty property) -> property.getOwner());
        addColumn("Host", (CommercialProperty property) -> property.getHost());
        addColumn("Address", (CommercialProperty property) -> property.getAddress());
        addColumn("Parking Slots", (CommercialProperty property) -> property.getParkingSlots());
        addColumn("Floors", (CommercialProperty property) -> property.getFloors());

        propertyTableView.setItems(FXCollections.observableArrayList(commercialProperties)); // Set data
    }

    private <T> void addColumn(String title, javafx.util.Callback<T, String> valueExtractor) {
        TableColumn<T, String> column = new TableColumn<>(title);
        column.setCellValueFactory(data -> new SimpleStringProperty(valueExtractor.call(data.getValue())));
        propertyTableView.getColumns().add((TableColumn<Object, ?>) column);
    }

    @FXML
    private void loadResidentialDataFromCSV() {
        residentialProperties.clear();
        String csvFileName = "/hoangvaha/frontend/sample/residentialProper.csv";

        try (InputStream inputStream = getClass().getResourceAsStream(csvFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    Property property = new Property(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
                    residentialProperties.add(property);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadCommercialDataFromCSV() {
        commercialProperties.clear();
        String csvFileName = "/hoangvaha/frontend/sample/commercialProper.csv";

        try (InputStream inputStream = getClass().getResourceAsStream(csvFileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    CommercialProperty property = new CommercialProperty(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    commercialProperties.add(property);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRadioButtonToggle(ActionEvent event) {
        if (residentialRadioButton.isSelected()) {
            loadResidentialDataFromCSV();
            setUpResidentialTable();
        } else if (commercialRadioButton.isSelected()) {
            loadCommercialDataFromCSV();
            setUpCommercialTable();
        }
    }

    // Residential property class
    public static class Property {
        private final String owner;
        private final String host;
        private final String address;
        private final String availableBedrooms;
        private final String kitchenAvailability;
        private final String petFriendliness;

        public Property(String owner, String host, String address, String availableBedrooms, String kitchenAvailability, String petFriendliness) {
            this.owner = owner;
            this.host = host;
            this.address = address;
            this.availableBedrooms = availableBedrooms;
            this.kitchenAvailability = kitchenAvailability;
            this.petFriendliness = petFriendliness;
        }

        public String getOwner() {
            return owner;
        }

        public String getHost() {
            return host;
        }

        public String getAddress() {
            return address;
        }

        public String getAvailableBedrooms() {
            return availableBedrooms;
        }

        public String getKitchenAvailability() {
            return kitchenAvailability;
        }

        public String getPetFriendliness() {
            return petFriendliness;
        }
    }

    // Commercial property class
    public static class CommercialProperty {
        private final String owner;
        private final String host;
        private final String address;
        private final String parkingSlots;
        private final String floors;

        public CommercialProperty(String owner, String host, String address, String parkingSlots, String floors) {
            this.owner = owner;
            this.host = host;
            this.address = address;
            this.parkingSlots = parkingSlots;
            this.floors = floors;
        }

        public String getOwner() {
            return owner;
        }

        public String getHost() {
            return host;
        }

        public String getAddress() {
            return address;
        }

        public String getParkingSlots() {
            return parkingSlots;
        }

        public String getFloors() {
            return floors;
        }
    }
}
