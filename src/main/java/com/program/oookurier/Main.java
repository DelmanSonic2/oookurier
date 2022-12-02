package com.program.oookurier;

import com.program.oookurier.Support.DataBaseClass;

public class Main {
    public static void main(String[] args) {
        DataBaseClass dataBaseClass = new DataBaseClass();
        dataBaseClass.connect();
        System.out.println(dataBaseClass.createTables());
    }
}
