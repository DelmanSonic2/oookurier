package com.program.oookurier.Models;

public class UserModel {

    private int id; //Идентефикатор пользователя

    private String name; //Имя пользователя

    private String email; //Электронная почта

    private String phone; //Телефон

    private String password; //Пароль

    private int role = 0; //Роль пользователя (по умолчанию 0 - клиент)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
