package com.example.foodapp.model.DA;

import com.example.foodapp.model.DTO.ThamSoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThamSoDA {


    public static void initializeParameters(Connection connection) throws SQLException {
        String query = "SELECT HeSoBan, PhiShip FROM ThamSo LIMIT 1";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ThamSoDTO.heSoBan = resultSet.getDouble("HeSoBan");
                ThamSoDTO.phiShip = resultSet.getInt("PhiShip");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }


}
