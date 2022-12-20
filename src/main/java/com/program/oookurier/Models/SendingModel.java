package com.program.oookurier.Models;

public class SendingModel {

    private int id; //Идентефикатор

    private int delivery_user; //Доставщик

    private int picking_user; //Забирающий посылку

    private int sending_point; //Пункт отправки

    private int receiving_point; //Пункт получения

    private int sending_user; //Отправитель

    private int receiving_user; //Получатель

    private int delivery_method; //Способ доставки

    private int sending_method; //Способ отправки

    private int status = 0; //Статус

    private int picking_address; //Адрес, откуда забрать

    private int receiving_address; //Адрес, куда доставить

    private int transportation_price; //Стоимость доставки

    private int delivery_price; //Стоимость перевозки

    private int total_price; //Итоговая стоимость

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDelivery_user() {
        return delivery_user;
    }

    public void setDelivery_user(int delivery_user) {
        this.delivery_user = delivery_user;
    }

    public int getPicking_user() {
        return picking_user;
    }

    public void setPicking_user(int picking_user) {
        this.picking_user = picking_user;
    }

    public int getSending_point() {
        return sending_point;
    }

    public void setSending_point(int sending_point) {
        this.sending_point = sending_point;
    }

    public int getReceiving_point() {
        return receiving_point;
    }

    public void setReceiving_point(int receiving_point) {
        this.receiving_point = receiving_point;
    }

    public int getSending_user() {
        return sending_user;
    }

    public void setSending_user(int sending_user) {
        this.sending_user = sending_user;
    }

    public int getReceiving_user() {
        return receiving_user;
    }

    public void setReceiving_user(int receiving_user) {
        this.receiving_user = receiving_user;
    }

    public int getDelivery_method() {
        return delivery_method;
    }

    public void setDelivery_method(int delivery_method) {
        this.delivery_method = delivery_method;
    }

    public int getSending_method() {
        return sending_method;
    }

    public void setSending_method(int sending_method) {
        this.sending_method = sending_method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPicking_address() {
        return picking_address;
    }

    public void setPicking_address(int picking_address) {
        this.picking_address = picking_address;
    }

    public int getReceiving_address() {
        return receiving_address;
    }

    public void setReceiving_address(int receiving_address) {
        this.receiving_address = receiving_address;
    }

    public int getTransportation_price() {
        return transportation_price;
    }

    public void setTransportation_price(int transportation_price) {
        this.transportation_price = transportation_price;
    }

    public int getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(int delivery_price) {
        this.delivery_price = delivery_price;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
