package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AddUserAddressesFieldController {

    @FXML
    private ChoiceBox<String> cityBox;

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> userBox;

    Map<String, Integer> users = new HashMap<>();
    Map<String, Integer> cities = new HashMap<>();

    @FXML
    void initialize() {
        ResultSet usersRS = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_USERS);
        ResultSet citiesRS = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_CITIES);
        try {
            ObservableList<String> usersOL = FXCollections.observableArrayList();
            while (usersRS.next()) {
                int id = usersRS.getInt("id");
                String name = usersRS.getString("name");
                users.put(name, id);
                usersOL.add(name);
            }
            userBox.setItems(usersOL);
            userBox.setValue(usersOL.get(0));

            ObservableList<String> citiesOL = FXCollections.observableArrayList();
            while (citiesRS.next()) {
                int id = citiesRS.getInt("id");
                String name = citiesRS.getString("name");
                cities.put(name, id);
                citiesOL.add(name);
            }
            cityBox.setItems(citiesOL);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void create(ActionEvent event) {
        nameField.getStyleClass().removeAll("no_valid");
        userBox.getStyleClass().removeAll("no_valid");
        cityBox.getStyleClass().removeAll("no_valid");

        if (nameField.textProperty().getValue().length() == 0) {
            nameField.getStyleClass().add("no_valid");
            return;
        }

        if (cityBox.getValue() == null) {
            cityBox.getStyleClass().add("no_valid");
            return;
        }

        if (userBox.getValue() == null) {
            userBox.getStyleClass().add("no_valid");
            return;
        }


        boolean result = Globals.dataBaseClass.executeQuery("INSERT INTO " + DataBaseTables.DB_USER_ADDRESSES + " (name,\"city\",\"user\") VALUES ('" + nameField.textProperty().getValue() + "'," + cities.get(cityBox.getValue()) + "," + users.get(userBox.getValue()) + ")");
        if (result) {
            Globals.currentModal.close();
        }
    }

}
