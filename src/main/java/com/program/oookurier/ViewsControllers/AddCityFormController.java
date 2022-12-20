package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Globals;
import com.program.oookurier.Support.DataBaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCityFormController {

    @FXML
    private TextField nameField;



    @FXML
    void create(ActionEvent event) {
        nameField.getStyleClass().removeAll("no_valid");


        if (nameField.textProperty().getValue().length() < 3) {
            nameField.getStyleClass().add("no_valid");
            return;
        }

        DataBaseClass dataBaseClass = Globals.dataBaseClass;
        ResultSet resultSet = dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_CITIES + " WHERE lower(name) = '" + nameField.textProperty().getValue().toLowerCase() + "'");

        try {
            if (resultSet.next()) {
                System.out.println("Такой город уже существует");
                nameField.getStyleClass().add("no_valid");

                return;
            }
        } catch (SQLException e) {

        }

        boolean result = dataBaseClass.executeQuery("INSERT INTO " + DataBaseTables.DB_CITIES + " (\"name\") VALUES ('" + nameField.textProperty().getValue() + "')");

        if (result) {
            Globals.currentModal.close();
        } else {
            nameField.getStyleClass().add("no_valid");
        }

    }

}
