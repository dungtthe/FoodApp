package com.example.foodapp.view.main_view.information;

import static com.example.foodapp.model.DTO.DataCurrent.khachHangDTOCur;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.KhachHangDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnLuu, btnSua;
    EditText hoTen, sDT,eMail,diaChi;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSua = view.findViewById(R.id.btnEdit_ThongTinCaNhan);
        btnLuu = view.findViewById(R.id.btn_saveThongTinCaNhan);
        hoTen = view.findViewById(R.id.et_nameThongTinCaNhan);
        sDT = view.findViewById(R.id.et_phoneTTCN);
        eMail = view.findViewById(R.id.et_emailTTCN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView imageView=getView().findViewById(R.id.iv_avatarTTCN);
            imageView.setClipToOutline(true);
        }

        LoadThongTin(getContext());

    }

    private void LoadThongTin(Context context) {
        // Lấy ID của khách hàng hiện tại
        int id = khachHangDTOCur.getId();

        // Chuẩn bị câu truy vấn
        String query = "SELECT * FROM KhachHang WHERE ID = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, id));

        // Tạo mảng tham số để truyền vào khối lệnh cơ sở dữ liệu
        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        // Tạo đối tượng KhachHangDA để thực hiện truy vấn
        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess && !result.isEmpty()) {
                    // Nếu truy vấn thành công và có kết quả, cập nhật thông tin lên các view
                    KhachHangDTO khachHangDTO = result.get(0);
                    hoTen.setText(khachHangDTO.getHoTen());
                    sDT.setText(khachHangDTO.getsDT());
                    eMail.setText(khachHangDTO.getMail());
                } else {
                    // Ví dụ: Hiển thị thông báo lỗi hoặc để trống các view
                    hoTen.setText("");
                    sDT.setText("");
                    eMail.setText("");
                    Toast.makeText(context, "Không thể tải thông tin khách hàng", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);

        // Thực thi truy vấn
        khachHangDA.execute(params);
    }

}