package com.example.foodapp.model.DA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.model.DTO.KhachHangDTO;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.foodapp.R;

public class KhachHangDA extends AsyncTask<Object, Void, List<KhachHangDTO>> {
    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public KhachHangDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected List<KhachHangDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<KhachHangDTO> khachHangDTOList = new ArrayList<>();
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
                    if (query.contains("COUNT(*)")) {
                        KhachHangDTO khachHangDTO = new KhachHangDTO();
                        khachHangDTO.setHoTen(resultSet.getString("count"));
                        khachHangDTOList.add(khachHangDTO);
                    } else {
                        KhachHangDTO khachHangDTO = new KhachHangDTO();
                        khachHangDTO.setId(resultSet.getInt("ID"));
                        khachHangDTO.setHoTen(resultSet.getString("HoTen"));
                        khachHangDTO.setsDT(resultSet.getString("SoDienThoai"));
                        khachHangDTO.setMail(resultSet.getString("Email"));
                        khachHangDTO.settenTaiKhoan(resultSet.getString("TenTaiKhoan"));
                        khachHangDTO.setMatKhau(resultSet.getString("MatKhau"));
                        khachHangDTO.setDaXoa(resultSet.getBoolean("DaXoa"));
                        byte[] imgBytes = resultSet.getBytes("HinhAnh");
                        if (imgBytes != null) {
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgBytes);
                            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                            khachHangDTO.setHinhAnh(bitmap);
                        } else {
                            Bitmap defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_default_for_product);
                            khachHangDTO.setHinhAnh(defaultBitmap);
                        }
                        khachHangDTOList.add(khachHangDTO);
                    }
                }
                isSuccess = !khachHangDTOList.isEmpty();
            } else {
                isSuccess = statement.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                isSuccess = false;
                e.printStackTrace();
            }
        }
        return khachHangDTOList;
    }

    @Override
    protected void onPostExecute(List<KhachHangDTO> result) {
        if (callback != null) {
            if (!isSuccess) {
                Toast.makeText(context, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            }
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }



    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess);
    }
}