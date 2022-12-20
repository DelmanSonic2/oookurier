package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Globals;
import com.program.oookurier.Main;
import com.program.oookurier.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.sql.ResultSet;

public class AdminScene {


    int currentPage = 0;

    @FXML
    private Button createButton;

    @FXML
    private GridPane mainTable;

    @FXML
    private TextField searchField;

    @FXML
    private Label nameLabel;

    @FXML
    void toAddresses(ActionEvent event) {
        currentPage = 5;
        showTable();

    }

    @FXML
    void toCities(ActionEvent event) {
        currentPage = 1;
        showTable();
    }

    @FXML
    void toCreated(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        URL location = null;
        switch (currentPage) {
            case 1:
                location = Main.class.getResource("AddCityForm.fxml");
                break;
            case 2:
                location = Main.class.getResource("AddRolesForm.fxml");
                break;
            case 3:
                location = Main.class.getResource("AddSendingMethodForm.fxml");
                break;
            case 4:
                location = Main.class.getResource("AddUserForm.fxml");
                break;
            case 5:
                location = Main.class.getResource("AddUserAddressesField.fxml");
                break;
            case 6:
                location = Main.class.getResource("AddPickingMethodForm.fxml");
                break;
            case 7:
                location = Main.class.getResource("AddSendingForm.fxml");
                break;
            case 8:
                location = Main.class.getResource("AddFillialForm.fxml");
                break;
            case 9:
                location = Main.class.getResource("AddSendingStatusesForm.fxml");
                break;
        }

        if (location == null) return;

        try {
            loader.setLocation(location);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Globals.mainStage);
            stage.show();

            Globals.currentModal = stage;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        searchField.textProperty().addListener(e -> {
            showTable();
        });
    }

    void clearTable() {
        while (mainTable.getChildren().size() > 0)
            mainTable.getChildren().remove(0);
    }


    void showCities() {
        TableView tableView = new TableView<CityModel>();

        ObservableList<CityModel> cities = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_CITIES + where);
        try {
            while (rs.next()) {
                CityModel cityModel = new CityModel();
                cityModel.setId(rs.getInt("id"));
                cityModel.setName(rs.getString("name"));
                cities.add(cityModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tableView.setItems(cities);

        TableColumn<CityModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<CityModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<CityModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<CityModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);

    }

    void showRoles() {
        TableView<RoleModel> tableView = new TableView<>();

        ObservableList<RoleModel> roles = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_ROLES + where);
        try {
            while (rs_roles.next()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(rs_roles.getInt("id"));
                roleModel.setName(rs_roles.getString("name"));
                roles.add(roleModel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(roles);

        TableColumn<RoleModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<RoleModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<RoleModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<RoleModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);
    }

    void showSendingMethods() {
        TableView<SendingMethodModel> tableView = new TableView<>();

        ObservableList<SendingMethodModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_SENDING_METHODS + where);
        try {
            while (rs_roles.next()) {
                SendingMethodModel model = new SendingMethodModel();
                model.setId(rs_roles.getInt("id"));
                model.setName(rs_roles.getString("name"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<SendingMethodModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<SendingMethodModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<SendingMethodModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<SendingMethodModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);
    }

    void showUsers() {
        TableView<UserModel> tableView = new TableView<>();

        ObservableList<UserModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE a.name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT a.*,b.name as 'roleName' FROM " + DataBaseTables.DB_USERS + " a join (SELECT * FROM " + DataBaseTables.DB_ROLES + ") b on a.role = b.id " + where);
        try {
            while (rs_roles.next()) {
                UserModel model = new UserModel();
                model.setId(rs_roles.getInt("id"));
                model.setName(rs_roles.getString("name"));
                model.setRoleName(rs_roles.getString("roleName"));
                model.setRole(rs_roles.getInt("role"));
                model.setPhone(rs_roles.getString("phone"));
                model.setEmail(rs_roles.getString("email"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<UserModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<UserModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<UserModel, Integer> roleColumn = new TableColumn<>("Номер роли");
        roleColumn.setCellValueFactory(new PropertyValueFactory<UserModel, Integer>("role"));

        tableView.getColumns().add(roleColumn);

        TableColumn<UserModel, String> roleNameColumn = new TableColumn<>("Название роли");
        roleNameColumn.setCellValueFactory(new PropertyValueFactory<UserModel, String>("roleName"));

        tableView.getColumns().add(roleNameColumn);

        TableColumn<UserModel, String> phone = new TableColumn<>("Телефон");
        phone.setCellValueFactory(new PropertyValueFactory<UserModel, String>("phone"));

        tableView.getColumns().add(phone);

        TableColumn<UserModel, String> email = new TableColumn<>("Почта");
        email.setCellValueFactory(new PropertyValueFactory<UserModel, String>("email"));

        tableView.getColumns().add(email);

        TableColumn<UserModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);
    }

    void showUserAddresses() {
        TableView<UserAddressesModel> tableView = new TableView<>();

        ObservableList<UserAddressesModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE a.name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT a.*,b.name as 'userName', c.name as 'cityName' FROM " + DataBaseTables.DB_USER_ADDRESSES + " a join (SELECT * FROM " + DataBaseTables.DB_USERS + ") b on a.\"user\" = b.id join (SELECT * FROM " + DataBaseTables.DB_CITIES + ") c on a.city = c.id " + where);
        try {
            while (rs_roles.next()) {
                UserAddressesModel model = new UserAddressesModel();
                model.setId(rs_roles.getInt("id"));
                model.setAddress(rs_roles.getString("name"));
                model.setUser(rs_roles.getInt("user"));
                model.setUserName(rs_roles.getString("userName"));
                model.setCityName(rs_roles.getString("cityName"));
                model.setCity(rs_roles.getInt("city"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<UserAddressesModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<UserAddressesModel, Integer> roleColumn = new TableColumn<>("Номер пользователя");
        roleColumn.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, Integer>("user"));

        tableView.getColumns().add(roleColumn);

        TableColumn<UserAddressesModel, String> roleNameColumn = new TableColumn<>("Имя пользователя");
        roleNameColumn.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, String>("userName"));

        tableView.getColumns().add(roleNameColumn);

        TableColumn<UserAddressesModel, Integer> cityColumn = new TableColumn<>("Номер города");
        cityColumn.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, Integer>("city"));

        tableView.getColumns().add(cityColumn);

        TableColumn<UserAddressesModel, String> cityNameColumn = new TableColumn<>("Название города");
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, String>("cityName"));

        tableView.getColumns().add(cityNameColumn);

        TableColumn<UserAddressesModel, String> phone = new TableColumn<>("Адрес");
        phone.setCellValueFactory(new PropertyValueFactory<UserAddressesModel, String>("address"));

        tableView.getColumns().add(phone);


        mainTable.add(tableView, 0, 0);
    }

    void showDeliveryMethods() {
        TableView<DeliveryMethodModel> tableView = new TableView<>();

        ObservableList<DeliveryMethodModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_DELIVERY_METHODS + where);
        try {
            while (rs_roles.next()) {
                DeliveryMethodModel model = new DeliveryMethodModel();
                model.setId(rs_roles.getInt("id"));
                model.setName(rs_roles.getString("name"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<DeliveryMethodModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<DeliveryMethodModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<DeliveryMethodModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<DeliveryMethodModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);
    }

    void showOrderStatuses() {
        TableView<OrderStatusModel> tableView = new TableView<>();

        ObservableList<OrderStatusModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE name like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_SENDING_STATUSES + where);
        try {
            while (rs_roles.next()) {
                OrderStatusModel model = new OrderStatusModel();
                model.setId(rs_roles.getInt("id"));
                model.setName(rs_roles.getString("name"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<OrderStatusModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<OrderStatusModel, Integer>("id"));

        tableView.getColumns().add(idColumn);

        TableColumn<OrderStatusModel, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<OrderStatusModel, String>("name"));

        tableView.getColumns().add(nameColumn);

        mainTable.add(tableView, 0, 0);
    }


    void showFillials() {
        TableView<FillialModel> tableView = new TableView<>();

        ObservableList<FillialModel> data = FXCollections.observableArrayList();

        String where = "";
        if (!searchField.textProperty().getValue().equals("")) {
            where = " WHERE a.address like '" + searchField.textProperty().getValue() + "%'";
        }

        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT a.*, c.name as 'cityName' FROM " + DataBaseTables.DB_FILLIALS + " a join (SELECT * FROM " + DataBaseTables.DB_CITIES + ") c on a.city = c.id " + where);
        try {
            while (rs_roles.next()) {
                FillialModel model = new FillialModel();
                model.setId(rs_roles.getInt("id"));
                model.setAddress(rs_roles.getString("address"));
                model.setCityName(rs_roles.getString("cityName"));
                model.setCity(rs_roles.getInt("city"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<FillialModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<FillialModel, Integer>("id"));

        tableView.getColumns().add(idColumn);


        TableColumn<FillialModel, Integer> cityColumn = new TableColumn<>("Номер города");
        cityColumn.setCellValueFactory(new PropertyValueFactory<FillialModel, Integer>("city"));

        tableView.getColumns().add(cityColumn);

        TableColumn<FillialModel, String> cityNameColumn = new TableColumn<>("Название города");
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<FillialModel, String>("cityName"));

        tableView.getColumns().add(cityNameColumn);

        TableColumn<FillialModel, String> phone = new TableColumn<>("Адрес");
        phone.setCellValueFactory(new PropertyValueFactory<FillialModel, String>("address"));

        tableView.getColumns().add(phone);


        mainTable.add(tableView, 0, 0);
    }

    void showSendings() {
        TableView<SendingModel> tableView = new TableView<>();

        ObservableList<SendingModel> data = FXCollections.observableArrayList();


        ResultSet rs_roles = Globals.dataBaseClass.query("SELECT * FROM " + DataBaseTables.DB_SENDINGS);
        try {
            while (rs_roles.next()) {
                SendingModel model = new SendingModel();
                model.setId(rs_roles.getInt("id"));
                model.setSending_point(rs_roles.getInt("sending_point"));
                model.setSending_user(rs_roles.getInt("sending_user"));
                model.setSending_method(rs_roles.getInt("sending_method"));
                model.setPicking_user(rs_roles.getInt("picking_user"));
                model.setPicking_address(rs_roles.getInt("picking_address"));
                model.setDelivery_user(rs_roles.getInt("delivery_user"));
                model.setDelivery_method(rs_roles.getInt("delivery_method"));
                model.setDelivery_price(rs_roles.getInt("delivery_price"));
                model.setReceiving_point(rs_roles.getInt("receiving_point"));
                model.setReceiving_user(rs_roles.getInt("receiving_user"));
                model.setReceiving_address(rs_roles.getInt("receiving_address"));
                model.setStatus(rs_roles.getInt("status"));
                model.setTransportation_price(rs_roles.getInt("transportation_price"));
                data.add(model);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tableView.setItems(data);

        TableColumn<SendingModel, Integer> idColumn = new TableColumn<>("Номер");
        idColumn.setCellValueFactory(new PropertyValueFactory<SendingModel, Integer>("id"));

        tableView.getColumns().add(idColumn);


        mainTable.add(tableView, 0, 0);
    }


    void showTable() {
        try {


            clearTable();
            switch (currentPage) {
                case 1:
                    nameLabel.setText("Города");
                    showCities();
                    break;
                case 2:
                    nameLabel.setText("Роли");
                    showRoles();
                    break;
                case 3:
                    nameLabel.setText("Способы отправки");
                    showSendingMethods();
                    break;
                case 4:
                    nameLabel.setText("Пользователи");
                    showUsers();
                    break;
                case 5:
                    nameLabel.setText("Адреса пользователей");
                    showUserAddresses();
                    break;
                case 6:
                    nameLabel.setText("Способы получения");
                    showDeliveryMethods();
                    break;
                case 7:
                    nameLabel.setText("Отправления");
                    showSendings();
                    break;
                case 8:
                    nameLabel.setText("Филиалы");

                    showFillials();
                    break;
                case 9:
                    nameLabel.setText("Статусы отправлений");

                    showOrderStatuses();
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void toFillials(ActionEvent event) {
        currentPage = 8;
        showTable();

    }

    @FXML
    void toOrderStatuses(ActionEvent event) {
        currentPage = 9;
        showTable();

    }

    @FXML
    void toPickingMethods(ActionEvent event) {
        currentPage = 6;
        showTable();

    }

    @FXML
    void toRoles(ActionEvent event) {
        currentPage = 2;
        showTable();
    }

    @FXML
    void toSendingsMethods() {
        currentPage = 3;
        showTable();

    }

    @FXML
    void toSendings(ActionEvent event) {
        currentPage = 7;
        showTable();
    }

    @FXML
    void toUsers(ActionEvent event) {
        currentPage = 4;
        showTable();

    }

}
