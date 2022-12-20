package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Globals;
import com.program.oookurier.Support.DataBaseClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSendingMethodFormController {

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
        ResultSet resultSet = dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_SENDING_METHODS + " WHERE lower(name) = '" + nameField.textProperty().getValue().toLowerCase() + "'");

        try {
            if (resultSet.next()) {
                System.out.println("Такая роль уже существует");
                nameField.getStyleClass().add("no_valid");

                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        boolean result = dataBaseClass.executeQuery("INSERT INTO " + DataBaseTables.DB_SENDING_METHODS + " (\"name\") VALUES ('" + nameField.textProperty().getValue() + "')");

        if (result) {
            Globals.currentModal.close();
        } else {
            nameField.getStyleClass().add("no_valid");
        }

    }
}
