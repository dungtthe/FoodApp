package com.example.foodapp.model.DA;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.foodapp.model.DTO.SanPhamDTO;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.foodapp.R;

public class SanPhamDA extends AsyncTask<Object, Void, List<SanPhamDTO>> {

    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public SanPhamDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public String getTenSanPham(int sanPhamId) {
        String query = "SELECT TenSP FROM SanPham WHERE ID = ?";
        List<QueryParameter> queryParameters = new ArrayList<>();
        queryParameters.add(new QueryParameter(1, sanPhamId));

        List<SanPhamDTO> sanPhamDTOList = doInBackground(query, queryParameters.toArray());
        if (!sanPhamDTOList.isEmpty()) {
            return sanPhamDTOList.get(0).getTenSP();
        }
        return null;
    }

    @Override
    protected List<SanPhamDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<SanPhamDTO> sanPhamDTOList = new ArrayList<>();
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
                    SanPhamDTO sanPhamDTO = new SanPhamDTO();
                    sanPhamDTO.setId(resultSet.getInt("ID"));
                    sanPhamDTO.setTenSP(resultSet.getString("TenSP"));
                    sanPhamDTO.setLoai(resultSet.getInt("Loai"));
                    sanPhamDTO.setGiaNhap(resultSet.getInt("GiaNhap"));
                    sanPhamDTO.setSoLuongTon(resultSet.getInt("SoLuongTon"));
                    sanPhamDTO.setDaXoa(resultSet.getBoolean("DaXoa"));
                    sanPhamDTO.setMoTa(resultSet.getString("MoTa"));
                    byte[] imgBytes = resultSet.getBytes("HinhAnh");
                    if (imgBytes != null) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgBytes);
                        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
                        sanPhamDTO.setHinhAnh(bitmap);
                    } else {
                        Bitmap defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_default_for_product);
                        sanPhamDTO.setHinhAnh(defaultBitmap);
                    }
                    sanPhamDTOList.add(sanPhamDTO);
                }
                isSuccess = !sanPhamDTOList.isEmpty();
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

        return sanPhamDTOList;
    }

    @Override
    protected void onPostExecute(List<SanPhamDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }


    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<SanPhamDTO> result, boolean isSuccess);
    }
}
