package com.example.foodapp.model.DA;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.foodapp.model.DTO.BinhLuanDTO;
import com.example.foodapp.model.DTO.KhachHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BinhLuanDA extends AsyncTask<Object, Void, List<BinhLuanDTO>> {

    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public BinhLuanDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public List<BinhLuanDTO> getBinhLuanBySanPhamId(int sanPhamId) {
        String query = "SELECT BinhLuan.*, KhachHang.HoTen AS TenKhachHang FROM BinhLuan " +
                "INNER JOIN KhachHang ON BinhLuan.KhachHang_id = KhachHang.ID " +
                "WHERE BinhLuan.SanPham_id = ?";
        List<QueryParameter> queryParameters = new ArrayList<>();
        queryParameters.add(new QueryParameter(1, sanPhamId));

        return doInBackground(query, queryParameters.toArray());
    }

    public void insertBinhLuan(String noiDung, int khachHangId, int sanPhamId) {
        String query = "INSERT INTO BinhLuan (NoiDung, KhachHang_id, SanPham_id) VALUES (?, ?, ?)";
        List<QueryParameter> queryParameters = new ArrayList<>();
        queryParameters.add(new QueryParameter(1, noiDung));
        queryParameters.add(new QueryParameter(2, khachHangId));
        queryParameters.add(new QueryParameter(3, sanPhamId));

        execute(query, queryParameters.toArray());
    }


    @Override
    protected List<BinhLuanDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<BinhLuanDTO> binhLuanDTOList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseHelper.getConnection();
            statement = connection.prepareStatement(query);
            QueryParameter.setStatementParameters(statement, queryParameters);

            if (query.trim().toUpperCase().startsWith("SELECT")) {
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
                    binhLuanDTO.setId(resultSet.getInt("ID"));
                    binhLuanDTO.setNgayBL(resultSet.getTimestamp("NgayBL"));
                    binhLuanDTO.setNoiDung(resultSet.getString("NoiDung"));
                    binhLuanDTO.setKhachHangId(resultSet.getInt("KhachHang_id"));
                    binhLuanDTO.setSanPhamId(resultSet.getInt("SanPham_id"));

                    if(query.contains("TenKhachHang")) {
                        binhLuanDTO.setTenKhachHang(resultSet.getString("TenKhachHang"));
                    }
                    binhLuanDTOList.add(binhLuanDTO);
                }
                isSuccess = !binhLuanDTOList.isEmpty();
            } else {
                isSuccess = statement.executeUpdate() > 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            Log.d("DTT",e.getMessage());
            e.printStackTrace();
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

        return binhLuanDTOList;
    }

    @Override
    protected void onPostExecute(List<BinhLuanDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }

    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<BinhLuanDTO> result, boolean isSuccess);
    }
}
