package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Controllers.UserController;
import com.program.oookurier.Globals;
import com.program.oookurier.Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class AddUserFromController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private ChoiceBox<String> roles;

    Map<String, Integer> roleMap = new HashMap<>();

    @FXML
    void initialize() {
        ResultSet roleRS = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_ROLES);

        ObservableList<String> roleOL = FXCollections.observableArrayList();

        try {
            while (roleRS.next()) {
                String name = roleRS.getString("name");
                int id = roleRS.getInt("id");

                roleOL.add(name);

                roleMap.put(name, id);
            }
            roles.setItems(roleOL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void create(ActionEvent event) {
        nameField.getStyleClass().removeAll("no_valid");
        emailField.getStyleClass().removeAll("no_valid");
        phoneField.getStyleClass().removeAll("no_valid");
        passwordField.getStyleClass().removeAll("no_valid");

        if (nameField.textProperty().getValue().length() < 3) {
            nameField.getStyleClass().add("no_valid");
            return;
        }

        if (!phoneField.textProperty().getValue().matches("^\\d{10}$")) {
            phoneField.getStyleClass().add("no_valid");
            return;
        }

        if (!emailField.textProperty().getValue().matches("(.*)@(.*).(.*)")) {
            emailField.getStyleClass().add("no_valid");
            return;
        }

        if (passwordField.textProperty().getValue().length() < 3) {
            passwordField.getStyleClass().add("no_valid");
            return;
        }

        if (roles.getValue() == null) {
            roles.getStyleClass().add("no_valid");
            return;
        }


        UserModel userModel = new UserModel();
        userModel.setEmail(emailField.textProperty().getValue());
        userModel.setName(nameField.textProperty().getValue());
        userModel.setPassword(passwordField.textProperty().getValue());
        userModel.setPhone(phoneField.textProperty().getValue());
        userModel.setRole(roleMap.get(roles.getValue()));

        UserController userController = new UserController();

        //Получаем ответ
        int result = userController.register(userModel);

        if (result == 1) {
            Globals.currentModal.close();
        }
    }

}
