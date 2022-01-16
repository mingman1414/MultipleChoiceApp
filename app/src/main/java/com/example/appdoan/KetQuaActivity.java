package com.example.appdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class KetQuaActivity extends AppCompatActivity {

    private TextView txtDiem;
    private Button btnHome;
    private Button btnThemDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        AnhXa();

        Intent intent = getIntent();
        int diem = intent.getIntExtra("diem", 0);
        txtDiem.setText("Điểm: " + diem);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NhapDiem();
            }
        });
    }

    private void NhapDiem() {
//        Intent intent = getIntent();
//        int diem = intent.getIntExtra("diem", 0);
//
//        Intent intent2 = getIntent();
//        int idmonhoc = intent2.getIntExtra("id", 0);

        Intent intent1 = new Intent(KetQuaActivity.this, ThemDiemActivity.class);
        startActivity(intent1);
    }

    private void AnhXa() {
        txtDiem         = (TextView)    findViewById(R.id.txtDiem);
        btnHome         = (Button)      findViewById(R.id.btnHome);
        btnThemDiem     = (Button)      findViewById(R.id.buttonThem);
    }


}