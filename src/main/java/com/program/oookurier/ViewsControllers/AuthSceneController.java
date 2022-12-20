package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Controllers.UserController;
import com.program.oookurier.Globals;
import com.program.oookurier.Main;
import com.program.oookurier.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.management.Notification;
import javax.swing.*;

public class AuthSceneController {

    @FXML
    private TextField loginFleld;

    @FXML
    private PasswordField passwordField;

    @FXML
    void auth(ActionEvent event) {
        UserModel userModel = new UserModel();
        userModel.setEmail(loginFleld.textProperty().getValue());
        userModel.setPhone(loginFleld.textProperty().getValue());
        userModel.setPassword(passwordField.textProperty().getValue());
        UserController userController = new UserController();

        int result = userController.auth(userModel);

        if (result == 1) {
            if (Globals.currentUser.getRole() == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Вы вошли как администратор");
                alert.show();
                toAdminPanel();
            } else if (Globals.currentUser.getRole() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Вы вошли как пользователь");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Вы вошли как курьер");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ошибка авторизации");
            alert.show();
        }

        System.out.println(result);
    }

    @FXML
    void toAdminPanel() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("AdminScene.fxml"));
            Globals.mainStage.getScene().setRoot(loader.load());
        } catch (Exception ex) {
            System.out.println("Ошибка при включении формы администратора");
        }
    }

    @FXML
    void toRegister() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RegisterScene.fxml"));
            Globals.mainStage.getScene().setRoot(loader.load());
        } catch (Exception ex) {
            System.out.println("Ошибка при включении формы регистрации");
        }
    }
}
