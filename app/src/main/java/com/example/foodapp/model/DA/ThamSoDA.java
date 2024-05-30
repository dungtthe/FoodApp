package com.example.foodapp.model.DA;

import android.util.Log;

import com.example.foodapp.model.DTO.ThamSoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThamSoDA {

    public static void updateParameters() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseHelper.getConnection();
            String query = "SELECT HeSoBan, PhiShip FROM ThamSo WHERE ThamSoID = 1";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ThamSoDTO.heSoBan = resultSet.getDouble("HeSoBan");
                ThamSoDTO.phiShip = resultSet.getInt("PhiShip");
            }
        } catch (SQLException | ClassNotFoundException e) {


        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }


}


