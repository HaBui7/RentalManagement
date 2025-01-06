package hoangvaha.frontend.controllers.OwnerControllers;

import hoangvaha.frontend.Models.CommercialProperty;
import hoangvaha.frontend.Models.ResidentialProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OwnerController {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button sortButton;
    @FXML
    private TableView<Object> propertyTableView;

    private ObservableList<ResidentialProperty> residentialProperties;


    @FXML
    public void initialize() {
        residentialProperties = FXCollections.observableArrayList();

        loadResidentialDataFromCSV();
        setUpResidentialTable();
    }

    private void setUpResidentialTable() {
        propertyTableView.getColumns().clear();
        addColumn("Owner", ResidentialProperty::getOwner);
        addColumn("Host", ResidentialProperty::getHost);
        addColumn("Address", ResidentialProperty::getAddress);
        addColumn("Available Bedrooms", ResidentialProperty::getAvailableBedrooms);
        addColumn("Kitchen Availability", ResidentialProperty::getKitchenAvailability);
        addColumn("Pet Friendliness", ResidentialProperty::getPetFriendliness);

        propertyTableView.setItems(FXCollections.observableArrayList(residentialProperties));
    }


    private <T> void addColumn(String title, Callback<T, String> valueExtractor) {
        TableColumn<T, String> column = new TableColumn<>(title);
        column.setCellValueFactory(data -> new SimpleStringProperty(valueExtractor.call(data.getValue())));
        propertyTableView.getColumns().add((TableColumn<Object, ?>) column);
    }

    @FXML
    private void loadResidentialDataFromCSV() {
        residentialProperties.clear();
        loadDataFromCSV("/hoangvaha/frontend/sample/residentialProper.csv", line -> {
            String[] fields = line.split(",");
            if (fields.length == 6) {
                return new ResidentialProperty(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
            }
            return null;
        }, residentialProperties);
    }

    private <T> void loadDataFromCSV(String fileName, Callback<String, T> mapper, ObservableList<T> list) {
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                T item = mapper.call(line);
                if (item != null) {
                    list.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
