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
import com.example.foodapp.view.start.SingupActivity;

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

        //Hiện thưc
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cho phép chỉnh sửa các EditText
                hoTen.setEnabled(true);
                sDT.setEnabled(true);
                eMail.setEnabled(true);

                // Hiển thị nút Lưu
                btnLuu.setVisibility(View.VISIBLE);
            }
        });

        //Lưu thông tin thay đổi
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTenMoi = hoTen.getText().toString().trim();
                String sDTMoi = sDT.getText().toString().trim();
                String eMailMoi = eMail.getText().toString().trim();
                if(hoTenMoi.isEmpty() || sDTMoi.isEmpty() || eMailMoi.isEmpty())
                {
                    Toast.makeText(getContext(), "Thông tin không đầy đủ", Toast.LENGTH_LONG).show();
                    return;
                }
                // Kiểm tra email có chứa ký tự '@'
                if (!eMailMoi.contains("@")) {
                    Toast.makeText(getContext(), "Email không hợp lệ, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    eMail.requestFocus();
                    eMail.selectAll();
                    return;
                }
                UpdateThongTin(hoTenMoi,sDTMoi,eMailMoi,getContext());
                // Sau khi lưu thông tin, các EditText trở lại trạng thái không thể chỉnh sửa
                hoTen.setEnabled(false);
                sDT.setEnabled(false);
                eMail.setEnabled(false);

                // Ẩn nút Lưu
                btnLuu.setVisibility(View.GONE);

            }
        });

    }

    private void LoadThongTin(Context context)
    {
        hoTen.setText(khachHangDTOCur.getHoTen());
        sDT.setText(khachHangDTOCur.getsDT());
        eMail.setText(khachHangDTOCur.getMail());
    }




    private void UpdateThongTin(String hoten, String sDt, String eMail , Context context) {
        int id = khachHangDTOCur.getId();
        String query = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ?, Email = ? WHERE ID = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, hoten));
        parameters.add(new QueryParameter(2, sDt));
        parameters.add(new QueryParameter(3, eMail));
        parameters.add(new QueryParameter(4, id));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess) {
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, context);
        khachHangDA.execute(params);
    }


}