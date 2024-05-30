package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.model.DA.KhachHangDA;
import com.example.foodapp.model.DA.QueryParameter;
import com.example.foodapp.model.DTO.KhachHangDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgetPassword extends AppCompatActivity {

    Button btnXacNhan, btnDangKy, btnDangNhap;
    EditText edtEmail, edtTenTk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnDangKy = findViewById(R.id.btnDangKyQMK);
        btnDangNhap = findViewById(R.id.btnDangNhapQMK);
        btnXacNhan = findViewById(R.id.btnXacNhanQMK);
        edtTenTk = findViewById(R.id.edtTenTKQMK);
        edtEmail = findViewById(R.id.edtEmailQMK);

        //Nav qua view đăng kí
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangkyview = new Intent(ForgetPassword.this,SingupActivity.class);
                startActivity(dangkyview);
            }
        });

        //Nav qua view đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangnhapview = new Intent(ForgetPassword.this,Login.class);
                startActivity(dangnhapview);
            }
        });

        //Đổi mật khẩu
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTenTk.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                doiMatKhau(username,email,ForgetPassword.this);
            }
        });




    }

    //Tạo password ngẫu nhiên
    public String passwordRandom() {
        Random random = new Random();
        int passwordLength = 6; // Độ dài mật khẩu là 6 ký tự
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }
    //Đổi mật khẩu
    public void doiMatKhau(String tenTK, String eMail, Context context)
    {
        String password = passwordRandom();
        String query = "UPDATE KhachHang SET MatKhau = ? WHERE TenTaiKhoan = ? AND Email = ?";
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(1, password));
        parameters.add(new QueryParameter(2, tenTK));
        parameters.add(new QueryParameter(3, eMail));

        Object[] params = new Object[parameters.size() + 1];
        params[0] = query;
        for (int i = 0; i < parameters.size(); i++) {
            params[i + 1] = parameters.get(i);
        }

        KhachHangDA khachHangDA = new KhachHangDA(new KhachHangDA.DatabaseCallback() {
            @Override
            public void onQueryExecuted(String query, List<KhachHangDTO> result, boolean isSuccess) {
                if (isSuccess)
                {
                    Toast.makeText(context, "Mật khẩu đã được gửi qua mail", Toast.LENGTH_LONG).show();
                    SendMail(password,eMail);
                    Intent dangnhapview = new Intent(ForgetPassword.this, Login.class);
                    startActivity(dangnhapview);
                }
                else
                {
                    Toast.makeText(context, "Tài khoản hoặc email không khớp", Toast.LENGTH_LONG).show();
                }
            }
        },context);
        khachHangDA.execute(params);

    }

    //Gửi mật khẩu qua mail
    public void SendMail(String password, String email)
    {
        try{
        String stringSenderEmail = "privateclinicse104@gmail.com";
        String stringreceiverEmail = email;
        String stringPasswordSenderEmail = "ibap lpjv sqrf vrsq";

        String stringhost = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", stringhost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth", "true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(stringSenderEmail,stringPasswordSenderEmail);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(stringreceiverEmail));
        mimeMessage.setSubject("Khôi phục mật khẩu");
        String emailContent = "Mật khẩu mới là <b>" + password + "</b>";
        mimeMessage.setContent(emailContent, "text/html; charset=utf-8");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Transport.send(mimeMessage);
                }
                catch (MessagingException e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        }
    catch (AddressException e)
    {
        e.printStackTrace();
    }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

}