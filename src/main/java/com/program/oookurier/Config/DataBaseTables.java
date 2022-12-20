package com.program.oookurier.Config;

public  class DataBaseTables {

    public static final String DB_USERS = Settings.tablePrefix + "users"; //Пользователи

    public static final String DB_CITIES = Settings.tablePrefix + "cities"; //Города

    public static final String DB_SENDING_METHODS = Settings.tablePrefix + "sending_methods"; //Способы отправки

    public static final String DB_DELIVERY_METHODS = Settings.tablePrefix + "delivery_methods"; //Способы доставки

    public static final String DB_SENDING_STATUSES = Settings.tablePrefix + "sending_statuses"; //Статусы отправлений

    public static final String DB_USER_ADDRESSES = Settings.tablePrefix + "user_addresses"; //Адреса пользователей

    public static final String DB_FILLIALS = Settings.tablePrefix + "fillials"; //Филлиалы

    public static final String DB_SENDINGS = Settings.tablePrefix + "sendings"; //отправления

    public static final String DB_ROLES = Settings.tablePrefix + "roles";//Роли

}
