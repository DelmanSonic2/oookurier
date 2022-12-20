package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AddFillialFormController {

    @FXML
    private TextField addressField;

    @FXML
    private ChoiceBox<String> cityBox;


    Map<String, Integer> cityList = new HashMap<>();

    @FXML
    void initialize() {
        ResultSet rs = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_CITIES);

        ObservableList<String> citites = FXCollections.observableArrayList();

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                citites.add(name);
                cityList.put(name, id);
            }
            cityBox.setItems(citites);
            cityBox.setValue(citites.get(0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void create(ActionEvent event) {

        addressField.getStyleClass().removeAll("no_valid");
        cityBox.getStyleClass().removeAll("no_valid");


        if (addressField.textProperty().getValue().length() < 3) {
            addressField.getStyleClass().add("no_valid");
            return;
        }

        if (cityBox.getValue() == null || cityBox.getValue().length() == 0) {
            cityBox.getStyleClass().add("no_valid");
            return;
        }


        int city = cityList.get(cityBox.getValue());

        boolean result = Globals.dataBaseClass.executeQuery("INSERT INTO " + DataBaseTables.DB_FILLIALS + " (address, city) VALUES ('" + addressField.textProperty().getValue() + "'," + city + ")");

        if (result) {
            Globals.currentModal.close();
        }

    }

}
