package com.example.appdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThemDiemActivity extends AppCompatActivity {

    private EditText edtTen;
    private EditText edtDiem;
    private EditText edtMonHoc;
    private TextView txtID;
    private Button btnNhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_diem);
        AnhXa();

        Intent intent = getIntent();
        int diem = intent.getIntExtra("diem", 0);
        String tenMonHoc = intent.getStringExtra("tenmonhoc");

        edtDiem.setText(diem+"");
        edtMonHoc.setText(tenMonHoc);
    }

    private void AnhXa() {
        edtTen      = (EditText)    findViewById(R.id.editTextTen);
        edtDiem     = (EditText)    findViewById(R.id.editTextDiem);
        edtMonHoc   = (EditText)    findViewById(R.id.editTextMon);
        txtID       = (TextView)    findViewById(R.id.textID);
        btnNhap     = (Button)      findViewById(R.id.buttonThem);
    }
}