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
        System.out.println(result);
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
