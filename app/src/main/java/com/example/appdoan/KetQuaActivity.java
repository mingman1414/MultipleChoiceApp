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
    private TextView txtMon;
    private TextView txtIdMon;
    private Button btnHome;
    private Button btnThemDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        AnhXa();

        Intent intent = getIntent();
        int diem = intent.getIntExtra("diem", 0);
        txtDiem.setText(diem+"");

        String tenmonhoc = intent.getStringExtra("tenmonhoc");
        txtMon.setText(tenmonhoc);

        String idmonhoc = intent.getStringExtra("idmonhoc");
        txtIdMon.setText(idmonhoc);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                startActivity(new Intent(KetQuaActivity.this, MainActivity.class));
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
        String diem = txtDiem.getText().toString();
        String tenmonhoc = txtMon.getText().toString();
        String idmonhoc = txtIdMon.getText().toString();

        Intent intent = new Intent(KetQuaActivity.this, ThemDiemActivity.class);
        intent.putExtra("tenmonhoc", tenmonhoc);
        intent.putExtra("diem", diem);
        intent.putExtra("idmonhoc", idmonhoc);
        startActivity(intent);
    }

    private void AnhXa() {
        txtDiem         = (TextView)    findViewById(R.id.txtDiem);
        txtMon          = (TextView)    findViewById(R.id.txtMon);
        txtIdMon        = (TextView)    findViewById(R.id.txtMonHocID);
        btnHome         = (Button)      findViewById(R.id.btnHome);
        btnThemDiem     = (Button)      findViewById(R.id.btnNhapDiem);
    }


}