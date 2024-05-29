package com.example.foodapp.model.DA;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.model.DTO.VoucherDTO;
import com.example.foodapp.view.main_view.MainViewActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VoucherDA extends AsyncTask<Object, Void, List<VoucherDTO>> {
    private String query;
    private DatabaseCallback callback;
    private boolean isSuccess = false;
    private Context context;

    public VoucherDA(DatabaseCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected List<VoucherDTO> doInBackground(Object... params) {
        query = (String) params[0];
        List<QueryParameter> queryParameters = new ArrayList<>();

        for (int i = 1; i < params.length; i++) {
            if (params[i] instanceof QueryParameter) {
                queryParameters.add((QueryParameter) params[i]);
            }
        }

        List<VoucherDTO> voucherDTOList = new ArrayList<>();
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
                    VoucherDTO voucherDTO = new VoucherDTO();
                    voucherDTO.setId(resultSet.getInt("ID"));
                    voucherDTO.setTenVoucher(resultSet.getString("TenVoucher"));
                    voucherDTO.setChiTiet(resultSet.getString("ChiTiet"));
                    voucherDTO.setNgayBatDau(resultSet.getTimestamp("NgayBatDau"));
                    voucherDTO.setNgayKetThuc(resultSet.getTimestamp("NgayKetThuc"));
                    voucherDTO.setGiaTri(resultSet.getInt("GiaTri"));
                    voucherDTO.setSoLuong(resultSet.getInt("SoLuong"));
                    voucherDTO.setDaXoa(resultSet.getBoolean("DaXoa"));
                    voucherDTOList.add(voucherDTO);
                }
                isSuccess = !voucherDTOList.isEmpty();
            } else {
                isSuccess = statement.executeUpdate() > 0;
            }

        } catch (ClassNotFoundException | SQLException e) {
            isSuccess = false;
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
                isSuccess = false;
                e.printStackTrace();
            }
        }

        return voucherDTOList;
    }

    @Override
    protected void onPostExecute(List<VoucherDTO> result) {
        if (callback != null) {
            callback.onQueryExecuted(query, result, isSuccess);
        }
    }

    public interface DatabaseCallback {
        void onQueryExecuted(String query, List<VoucherDTO> result, boolean isSuccess);
    }



    private static void selectAllVouchersForToday(Context context, DatabaseCallback callback) {
        String query = "SELECT * FROM Voucher WHERE NgayBatDau <= CURRENT_TIMESTAMP() AND (NgayKetThuc >= CURRENT_TIMESTAMP() OR NgayKetThuc IS NULL) AND DaXoa = FALSE;";

        VoucherDA voucherDA = new VoucherDA(callback, context);
        voucherDA.execute(query);
    }

    public static void getAllVoucherForToday(Context context){
        VoucherDA.selectAllVouchersForToday(context, new VoucherDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<VoucherDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {




                    // Xử lý danh sách các voucher lấy được ở đây nha
                    for (VoucherDTO voucher : result) {
                        Log.d("DTT",voucher.getTenVoucher()+"=="+voucher.getNgayBatDau()+"=="+voucher.getNgayKetThuc());
                    }
                } else {
                }
            }
        });

    }
}
