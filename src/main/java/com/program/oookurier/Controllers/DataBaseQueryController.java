package com.program.oookurier.Controllers;

import com.program.oookurier.Globals;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DataBaseQueryController {

    private ResultSet rs;

    ObservableList<String> ol = FXCollections.observableArrayList();

    private Map<String, Integer> map = new HashMap<>();

    private int count = 0;

    private String index = "name";


    public DataBaseQueryController(String query) {

                rs = Globals.dataBaseClass.query(query);
        try {


            while (rs.next()) {
                count++;
                String str = rs.getString("name");
                map.put(str, rs.getInt("id"));
                ol.add(str);
            }
        } catch (Exception exception) {

        }
    }

    public DataBaseQueryController(String query, String name) {

        rs = Globals.dataBaseClass.query(query);
        try {


            while (rs.next()) {
                count++;
                String str = rs.getString(name);
                map.put(str, rs.getInt("id"));
                ol.add(str);
            }
        } catch (Exception exception) {

        }
    }

    public int getCount() {
        return count;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public ObservableList<String> getOl() {
        return ol;
    }

}
