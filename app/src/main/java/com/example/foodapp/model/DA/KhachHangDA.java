package com.example.foodapp.model.DA;

import android.content.Context;
import android.os.AsyncTask;

import com.example.foodapp.model.DTO.KhachHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDA extends AsyncTask<Object, Void, List<KhachHangDTO>> {
    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public KhachHangDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    protected List<KhachHangDTO> doInBackground(Object... params) {
        List<KhachHangDTO> result = new ArrayList<>();
        query = (String) params[0];
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseHelper.getConnection();
            statement = connection.prepareStatement(query);

            if (query.toLowerCase().startsWith("select")) {
                String tenTaiKhoan = (String) params[1];
                statement.setString(1, tenTaiKhoan);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    KhachHangDTO khachHang = new KhachHangDTO();
                    khachHang.setHoTen(resultSet.getString("HoTen"));
                    khachHang.setsDT(resultSet.getString("SoDienThoai"));
                    khachHang.setMail(resultSet.getString("Email"));
                    khachHang.settenTaiKhoan(resultSet.getString("TenTaiKhoan"));
                    khachHang.setMatKhau(resultSet.getString("MatKhau"));
                    result.add(khachHang);
                }
                isSuccess = true;
            } else if (query.toLowerCase().startsWith("insert")) {
                KhachHangDTO khachHangDTO = (KhachHangDTO) params[1];
                statement.setString(1, khachHangDTO.getHoTen());
                statement.setString(2, khachHangDTO.getsDT());
                statement.setString(3, khachHangDTO.getMail());
                statement.setString(4, khachHangDTO.gettenTaiKhoan());
                statement.setString(5, khachHangDTO.getMatKhau());
                statement.setBoolean(6, false); // Mặc định DaXoa là false
                isSuccess = statement.executeUpdate() > 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<KhachHangDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }

    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess);
    }
}
