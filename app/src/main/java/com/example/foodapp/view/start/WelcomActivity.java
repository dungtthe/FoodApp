package com.example.foodapp.view.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.foodapp.R;

public class WelcomActivity extends AppCompatActivity {

    Button btnBatDau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        btnBatDau = findViewById(R.id.btnbatdau);

        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myintent = new Intent(WelcomActivity.this, Login.class);
                    //Chuyển activity
                    startActivity(myintent);
                }
                catch (Exception e){
                    Log.d("DTT",e.getMessage());

                }
            }
        });

    }
}