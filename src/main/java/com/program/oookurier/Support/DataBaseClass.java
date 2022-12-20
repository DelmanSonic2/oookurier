package com.program.oookurier.Support;


import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Config.Settings;

import java.sql.*;
import java.util.Properties;
import java.util.Set;

public class DataBaseClass {
    private Connection connection;

    public void connect() {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Properties properties = new Properties();
            properties.put("user", Settings.databaseUser);
            properties.put("password", Settings.databasePassword);
            connection = DriverManager.getConnection("jdbc:sqlserver://" + Settings.databaseServer + ";" + "databaseName=" + Settings.databaseName + ";", properties);
            System.out.println("Соединение с базой данных прошло успешно");
        } catch (Exception exception) {
            System.out.println("Ошибка подключения к базе данных: \n" + exception.getMessage());
        }
    }

    public ResultSet query(String query) {
        try {
            Statement preparedStatement = connection.createStatement();

            return preparedStatement.executeQuery(query);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка " + ex.getMessage() + " при выполнении запроса:\n" + query);
            return null;
        }
    }

    public boolean executeQuery(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            return true;
        } catch (Exception exception) {
            System.out.println("Произошла ошибка " + exception.getMessage() + " при выполнении запроса:\n" + query);
            return false;
        }
    }

    public boolean createTables() {
        String sql =

                "CREATE TABLE " + DataBaseTables.DB_CITIES + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        ");\n\n" +

                        "CREATE TABLE  " + DataBaseTables.DB_SENDING_METHODS + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_DELIVERY_METHODS + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_SENDING_STATUSES + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_ROLES + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        ");\n\n" +

                        "CREATE TABLE   " + DataBaseTables.DB_USERS + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "email VARCHAR(50) NOT NULL,\n" +
                        "password VARCHAR(50) NOT NULL,\n" +
                        "phone VARCHAR(50) NOT NULL,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        "created VARCHAR(50) NOT NULL,\n" +
                        "role int,\n" +
                        "CONSTRAINT FK_PICKING_USER FOREIGN KEY (role)\n" +
                        "REFERENCES " + DataBaseTables.DB_ROLES + " (id)" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_USER_ADDRESSES + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "\"user\" int NOT NULL,\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        "\"city\" int NOT NULL,\n" +
                        "CONSTRAINT FK_USER FOREIGN KEY (\"user\")\n" +
                        "REFERENCES \"" + DataBaseTables.DB_USERS + "\" (id)," +
                        "CONSTRAINT FK_USER_ADDRESSED_USER FOREIGN KEY (\"city\")\n" +
                        "REFERENCES \"" + DataBaseTables.DB_CITIES + "\" (id)" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_FILLIALS + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "city int NOT NULL,\n" +
                        "address VARCHAR(50) NOT NULL,\n" +
                        "CONSTRAINT FK_CITY FOREIGN KEY (city)\n" +
                        "REFERENCES \"" + DataBaseTables.DB_CITIES + "\" (id)" +
                        ");\n\n" +

                        "CREATE TABLE " + DataBaseTables.DB_SENDINGS + " (\n" +
                        "id int IDENTITY PRIMARY KEY,\n" +
                        "picking_user int,\n" +
                        "delivery_user int,\n" +
                        "sending_point int NOT NULL,\n" +
                        "receiving_point int NOT NULL,\n" +
                        "sending_user int,\n" +
                        "receiving_user int,\n" +
                        "delivery_method int NOT NULL,\n" +
                        "sending_method int NOT NULL,\n" +
                        "status int,\n" +
                        "picking_address int,\n" +
                        "receiving_address int,\n" +
                        "transportation_price int,\n" +
                        "delivery_price int,\n" +
                        "total_price int,\n" +
                        "CONSTRAINT FK_PICKING_USER_SENDING FOREIGN KEY (picking_user)\n" +
                        "REFERENCES " + DataBaseTables.DB_USERS + " (id)," +
                        "CONSTRAINT FK_DELIVERY_USER_USER_SENDING FOREIGN KEY (delivery_user)\n" +
                        "REFERENCES " + DataBaseTables.DB_USERS + " (id)," +
                        "CONSTRAINT FK_SENDING_USER_USER_SENDING FOREIGN KEY (sending_user)\n" +
                        "REFERENCES " + DataBaseTables.DB_USERS + " (id)," +
                        "CONSTRAINT FK_RECEIVING_USER_USER_SENDING FOREIGN KEY (receiving_user)\n" +
                        "REFERENCES " + DataBaseTables.DB_USERS + " (id)," +
                        "CONSTRAINT FK_DELIVERY_METHOD_USER_SENDING FOREIGN KEY (delivery_method)\n" +
                        "REFERENCES " + DataBaseTables.DB_DELIVERY_METHODS + " (id)," +
                        "CONSTRAINT FK_SENDING_METHOD_USER_SENDING FOREIGN KEY (sending_method)\n" +
                        "REFERENCES " + DataBaseTables.DB_SENDING_METHODS + " (id)," +
                        "CONSTRAINT FK_STATUS_FOREIGN_USER_SENDING FOREIGN KEY (status)\n" +
                        "REFERENCES " + DataBaseTables.DB_SENDING_STATUSES + " (id)," +
                        "CONSTRAINT FK_SENDING_POINT_USER_SENDING FOREIGN KEY (sending_point)\n" +
                        "REFERENCES " + DataBaseTables.DB_FILLIALS + " (id)," +
                        "CONSTRAINT FK_RECEIVING_POINT_USER_SENDING FOREIGN KEY (receiving_point)\n" +
                        "REFERENCES " + DataBaseTables.DB_FILLIALS + " (id)" +
                        ");\n\n" +
                        "INSERT INTO " + DataBaseTables.DB_ROLES + " (name) VALUES ('Администратор');\n\n" +
                        "INSERT INTO " + DataBaseTables.DB_ROLES + " (name) VALUES ('Пользователь');\n\n";

        return this.executeQuery(sql);
    }


}
