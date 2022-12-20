package com.program.oookurier.ViewsControllers;

import com.program.oookurier.Config.DataBaseTables;
import com.program.oookurier.Controllers.DataBaseQueryController;
import com.program.oookurier.Models.SendingModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddSendingFromController {

    @FXML
    private ChoiceBox<String> delivery_method;

    @FXML
    private TextField delivery_price;

    @FXML
    private ChoiceBox<String> delivery_user;

    @FXML
    private ChoiceBox<String> picking_address;

    @FXML
    private ChoiceBox<String> picking_user;

    @FXML
    private ChoiceBox<String> receiving_address;


    @FXML
    private ChoiceBox<String> sending_point;

    @FXML
    private ChoiceBox<String> receiving_point;

    @FXML
    private ChoiceBox<String> receiving_user;

    @FXML
    private ChoiceBox<String> sending_method;

    @FXML
    private ChoiceBox<String> sending_user;

    @FXML
    private TextField transportation_price;

    DataBaseQueryController delivery_methods = new DataBaseQueryController("SELECT * FROM " + DataBaseTables.DB_DELIVERY_METHODS);

    DataBaseQueryController sending_methods = new DataBaseQueryController("SELECT * FROM " + DataBaseTables.DB_SENDING_METHODS);

    DataBaseQueryController points = new DataBaseQueryController("SELECT * FROM " + DataBaseTables.DB_FILLIALS,"address");

    DataBaseQueryController users = new DataBaseQueryController("SELECT * FROM " + DataBaseTables.DB_USERS);

    DataBaseQueryController addresses = new DataBaseQueryController("SELECT * FROM " + DataBaseTables.DB_USER_ADDRESSES);

    @FXML
    void initialize() {
        delivery_method.setItems(delivery_methods.getOl());
        delivery_method.setValue(delivery_methods.getOl().get(0));

        sending_method.setItems(sending_methods.getOl());
        sending_method.setValue(sending_methods.getOl().get(0));

        delivery_user.setItems(users.getOl());
        delivery_user.setValue(users.getOl().get(0));

        sending_user.setItems(users.getOl());
        sending_user.setValue(users.getOl().get(0));

        picking_user.setItems(users.getOl());
        picking_user.setValue(users.getOl().get(0));

        receiving_user.setItems(users.getOl());
        receiving_user.setValue(users.getOl().get(0));

        receiving_point.setItems(points.getOl());
        receiving_point.setValue(points.getOl().get(0));

        sending_point.setItems(points.getOl());
        sending_point.setValue(points.getOl().get(0));

        picking_address.setItems(addresses.getOl());
        picking_address.setValue(addresses.getOl().get(0));

        receiving_address.setItems(addresses.getOl());
        receiving_address.setValue(addresses.getOl().get(0));

    }

    @FXML
    void create(ActionEvent event) {

        checkValidChoiceBox(delivery_method);
        checkValidChoiceBox(sending_method);
        checkValidChoiceBox(delivery_user);
        checkValidChoiceBox(sending_user);
        checkValidChoiceBox(picking_user);
        checkValidChoiceBox(receiving_user);
        checkValidChoiceBox(receiving_point);
        checkValidChoiceBox(sending_point);
        checkValidChoiceBox(picking_address);
        checkValidChoiceBox(receiving_address);

        SendingModel sendingModel = new SendingModel();
        sendingModel.setDelivery_method(delivery_methods.getMap().get(delivery_method.getValue()));
        sendingModel.setSending_method(sending_methods.getMap().get(sending_method.getValue()));
        sendingModel.setDelivery_user(users.getMap().get(delivery_user.getValue()));
        sendingModel.setSending_user(users.getMap().get(sending_user.getValue()));
        sendingModel.setPicking_user(users.getMap().get(picking_user.getValue()));
        sendingModel.setReceiving_user(users.getMap().get(receiving_user.getValue()));
        sendingModel.setReceiving_point(points.getMap().get(receiving_point.getValue()));
        sendingModel.setSending_point(points.getMap().get(sending_point.getValue()));
        sendingModel.setPicking_address(addresses.getMap().get(picking_address.getValue()));
        sendingModel.setReceiving_address(addresses.getMap().get(receiving_address.getValue()));
        sendingModel.setTransportation_price(Integer.parseInt(transportation_price.textProperty().get()));
        sendingModel.setDelivery_price(Integer.parseInt(delivery_price.textProperty().get()));
        sendingModel.setTotal_price(sendingModel.getTransportation_price() + sendingModel.getDelivery_price());

    }

    void checkValidChoiceBox(ChoiceBox<String> choiceBox) {
        if (choiceBox.getValue() == null || choiceBox.getValue().equals("")) {
            choiceBox.getStyleClass().add("no_valid");
        } else {
            choiceBox.getStyleClass().removeAll("no_valid");
        }
    }

}
