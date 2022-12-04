package com.program.oookurier.Controllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Config.Settings;
import com.program.oookurier.Globals;
import com.program.oookurier.Models.UserModel;
import com.program.oookurier.Support.DataBaseClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserController {

    //Регистрация пользователя
    public int register(UserModel userModel) {

        if (userModel.getEmail() == null || userModel.getEmail().length() == 0)
            return -2; //-2 обозначает недостаточно данных

        if (userModel.getPhone() == null || userModel.getPhone().length() < 10) return -2;

        if (userModel.getPassword() == null || userModel.getPassword().length() < 8) return -2;

        if (userModel.getName() == null || userModel.getName().length() < 3) return -2;

        ResultSet resultSet = Globals.dataBaseClass.query(
                "SELECT * FROM \"" + DataBaseTables.DB_USERS + "\" " +
                        "WHERE \"phone\"='" + userModel.getPhone() + "' or \"email\"='" + userModel.getEmail() + "'");

        try {
            if (resultSet.next()) return -1; //-1 обозначает, что пользователь с таким номером или почтой уже существует
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.now();

        if (Globals.dataBaseClass.executeQuery("INSERT INTO \"" + DataBaseTables.DB_USERS + "\" " +
                "(\"email\",\"phone\",\"name\",\"password\",\"created\",\"role\") " +
                "VALUES " +
                "('" + userModel.getEmail() + "','" + userModel.getPhone() + "','" + userModel.getName() + "','" + userModel.getPassword() + "','" + dateTimeFormatter.format(localDateTime) + "','" + userModel.getRole() + "')")) {
            return 1; //Регистрация прошла успешно
        } else {
            return 0; //Ошибка при регистрации
        }
    }

    //Авторизация пользователя
    public int auth(UserModel userModel) {
        if ((userModel.getEmail() == null || userModel.getEmail().length() == 0) && (userModel.getPhone() == null || userModel.getPhone().length() < 10))
            return -2; //-2 обозначает недостаточно данных

        if ((userModel.getPassword() == null || userModel.getPassword().length() < 8))
            return 0;

        ResultSet resultSet = Globals.dataBaseClass.query("SELECT * FROM \"" + DataBaseTables.DB_USERS + "\" WHERE \"email\"='" + userModel.getEmail() + "' or \"phone\"='" + userModel.getPhone() + "'");

        int count = 0;

        try {
            while (resultSet.next()) {
                count++;
                if (!resultSet.getString("password").equals(userModel.getPassword())) {
                    return 0;
                }
                UserModel model = new UserModel();
                model.setId(resultSet.getInt("id"));
                model.setPhone(resultSet.getString("phone"));
                model.setEmail(resultSet.getString("email"));
                model.setRole(resultSet.getInt("role"));
                model.setName(resultSet.getString("name"));
                model.setPassword(resultSet.getString("password"));
                Globals.currentUser = model;
                return 1;
            }
        } catch (Exception exception) {
            System.out.println("Ошибка получения результата " + exception.getMessage());
        }
        if (count == 0) {
            return 0; //Ошибка авторизации
        }
        return 0;
    }
}
