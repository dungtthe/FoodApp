package com.example.foodapp.model.DA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.foodapp.R;
import com.example.foodapp.model.DTO.DiaChiGiaoHangDTO;
import com.example.foodapp.model.DTO.SanPhamDTO;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiaChiGiaoHangDA extends AsyncTask<Object, Void, List<DiaChiGiaoHangDTO>> {
    private String query;
    private DiaChiGiaoHangDA.DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public DiaChiGiaoHangDA(DiaChiGiaoHangDA.DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected List<DiaChiGiaoHangDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<DiaChiGiaoHangDTO> diaChiGiaoHangDTOList = new ArrayList<>();
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
                    DiaChiGiaoHangDTO diaChiGiaoHangDTO = new DiaChiGiaoHangDTO();
                    diaChiGiaoHangDTO.setId(resultSet.getInt("ID"));
                    diaChiGiaoHangDTO.setDiaChi(resultSet.getString("DiaChi"));
                    diaChiGiaoHangDTO.setLoaiDiaChi(resultSet.getBoolean("LoaiDiaChi"));
                    diaChiGiaoHangDTO.setTenKhachHang(resultSet.getString("TenKhachHang"));
                    diaChiGiaoHangDTO.setsDT(resultSet.getString("SoDienThoai"));
                    diaChiGiaoHangDTO.setId_KhachHang(resultSet.getInt("KhachHang_id"));
                    diaChiGiaoHangDTOList.add(diaChiGiaoHangDTO);
                }
                isSuccess = !diaChiGiaoHangDTOList.isEmpty();
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

        return diaChiGiaoHangDTOList;
    }




    @Override
    protected void onPostExecute(List<DiaChiGiaoHangDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }


    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<DiaChiGiaoHangDTO> result, boolean isSuccess);
    }


}
