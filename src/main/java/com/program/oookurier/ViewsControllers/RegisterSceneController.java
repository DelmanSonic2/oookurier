package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Controllers.UserController;
import com.program.oookurier.Globals;
import com.program.oookurier.Main;
import com.program.oookurier.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class RegisterSceneController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    void register(ActionEvent event) {

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
            passwordField.getStyleClass().add("no_valud");
            return;
        }

        UserModel userModel = new UserModel();
        userModel.setEmail(emailField.textProperty().getValue());
        userModel.setName(nameField.textProperty().getValue());
        userModel.setPassword(passwordField.textProperty().getValue());
        userModel.setPhone(phoneField.textProperty().getValue());

        UserController userController = new UserController();

        //Получаем ответ
        int result = userController.register(userModel);

        System.out.println(result);
    }

    @FXML
    void toAuth() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("AuthScene.fxml"));
            Globals.mainStage.getScene().setRoot(loader.load());
        } catch (Exception ex) {
            System.out.println("Ошибка при включении формы авторизации");
        }
    }

}
