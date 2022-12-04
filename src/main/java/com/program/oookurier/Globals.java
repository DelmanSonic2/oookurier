package com.program.oookurier;

import com.program.oookurier.Models.UserModel;
import com.program.oookurier.Support.DataBaseClass;
import javafx.stage.Stage;

public class Globals {
    public static Stage mainStage; //Главное окно
    public static DataBaseClass dataBaseClass = new DataBaseClass(); //Глобальный класс базы данных

    public static UserModel currentUser; //Текущий пользователь
}
