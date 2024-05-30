package com.example.foodapp.view.main_view.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.R;

public class diachi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);

        // Get a reference to the LinearLayout
        LinearLayout linearLayout = findViewById(R.id.linearLayout_2);

        // Set a click listener on the LinearLayout
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your logic here when the LinearLayout is clicked
                // You can use 'this' or 'getApplicationContext()' to get the context
                Intent intent = new Intent(diachi.this, ThemDiaChi.class);
                startActivity(intent);
            }
        });
    }
}