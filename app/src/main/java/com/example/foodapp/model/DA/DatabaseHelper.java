package com.example.foodapp.model.DA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://192.168.250.104:33066/FoodApp";
    private static final String USER = "foodapp";
    private static final String PASSWORD = "123456";

    public static  String insert="INSERT";
    public static  String update="UPDATE";
    public static  String delete="DELETE";
    public static  String select="SELECT";



    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }





}
