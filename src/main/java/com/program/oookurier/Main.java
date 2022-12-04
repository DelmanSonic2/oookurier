package com.program.oookurier;

import com.program.oookurier.Support.DataBaseClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Globals.dataBaseClass.connect();
        Globals.dataBaseClass.createTables();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Globals.mainStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AuthScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
